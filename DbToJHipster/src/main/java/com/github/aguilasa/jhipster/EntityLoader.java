package com.github.aguilasa.jhipster;

import com.github.aguilasa.jhipster.generators.JdlWriter;
import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.jhipster.types.Relationship;
import com.github.aguilasa.jhipster.types.RelationshipType;
import com.github.aguilasa.metadata.*;
import com.github.aguilasa.utils.Converter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.*;

@Getter
@RequiredArgsConstructor
public class EntityLoader {

    private static final int MTM_SIZE = 2;
    private static final String LB = "\r\n";

    @NonNull
    private MetaDataLoader metaDataLoader;

    private Set<Entity> entities = new LinkedHashSet<>();
    private Map<RelationshipType, List<Relationship>> relationships = new LinkedHashMap<>();

    public void loadAll() throws SQLException {
        loadEntities();
        loadRelationships();
    }

    public void loadEntities() throws SQLException {
        entities.clear();
        metaDataLoader.loadAll();
        Set<Table> tables = metaDataLoader.getTables();
        for (Table table : tables) {
            entities.add(Converter.tableToEntity(table));
        }
    }

    public void loadRelationships() {
        relationships.clear();
        Set<Entity> remove = new LinkedHashSet<>();
        for (Entity entity : entities) {
            Table table = metaDataLoader.findTableByName(entity.getName());
            if (isManyToManyTable(table)) {
                addManyToManyRelationship(table);
                remove.add(entity);
            } else {
                List<ForeignKey> foreignKeys = table.getForeignKeys();
                foreignKeys.forEach(f -> {
                    addOneToOneRelationship(f);
                });
            }
        }
        for (Entity entity : remove) {
            entities.remove(entity);
        }
    }

    public Relationship createRelationshipFromColumn(Column fromColumn, Column toColumn, RelationshipType relationshipType) {
        Relationship relationship = new Relationship();
        Entity from = findEntityByName(fromColumn.getOwner().getName());
        Entity to = findEntityByName(toColumn.getOwner().getName());
        relationship.setFromEntity(from);
        relationship.setFromName(to.getName().toLowerCase());
        relationship.setToEntity(to);
        relationship.setToName(from.getName().toLowerCase());
        relationship.setRelationshipType(relationshipType);
        return relationship;
    }

    public Relationship createRelationshipFromForeignKey(ForeignKey foreignKey, RelationshipType relationshipType) {
        Relationship relationship = createRelationshipFromColumn( //
                foreignKey.getColumn(), //
                foreignKey.getReferenceColumn(), //
                relationshipType //
        );
        return relationship;
    }

    public void addOneToOneRelationship(ForeignKey foreignKey) {
        Relationship relationship = createRelationshipFromForeignKey(foreignKey, RelationshipType.OneToOne);
        addRelationship(relationship);
    }

    public void addOneToManyRelationship(ForeignKey foreignKey) {
        Relationship relationship = createRelationshipFromForeignKey(foreignKey, RelationshipType.OneToMany);
        addRelationship(relationship);
    }

    public void addManyToOneRelationship(ForeignKey foreignKey) {
        Relationship relationship = createRelationshipFromForeignKey(foreignKey, RelationshipType.ManyToOne);
        addRelationship(relationship);
    }

    public void addManyToManyRelationship(Table table) {
        List<ForeignKey> foreignKeys = table.getForeignKeys();
        Column fromColumn = foreignKeys.get(0).getReferenceColumn();
        Column toColumn = foreignKeys.get(1).getReferenceColumn();
        Relationship relationship = createRelationshipFromColumn(fromColumn, toColumn, RelationshipType.ManyToMany);
        addRelationship(relationship);
    }

    private boolean isManyToManyTable(Table table) {
        int columns_length = table.getColumns().size();
        int foreign_length = table.getForeignKeys().size();
        int primary_length = table.getPrimaryKey().getColumns().size();
        return columns_length == MTM_SIZE && columns_length == foreign_length && columns_length == primary_length;
    }

    public Entity findEntityByName(String entityName) {
        return entities.stream() //
                .filter(e -> e.getName().equalsIgnoreCase(entityName)) //
                .findFirst() //
                .orElseThrow(() -> new RuntimeException(String.format("Entidade '%s' não encontrada.", entityName)));
    }

    private void addRelationship(Relationship relationship) {
        RelationshipType relationshipType = relationship.getRelationshipType();
        if (!relationships.containsKey(relationshipType)) {
            relationships.put(relationshipType, new LinkedList<>());
        }
        List<Relationship> list = relationships.get(relationshipType);
        list.add(relationship);
    }

    public String toJdl() {
        JdlWriter writer = new JdlWriter();
        StringBuilder sb = new StringBuilder();
        for (Entity entity : entities) {
            sb.append(writer.entityToJdl(entity));
            sb.append(LB).append(LB);
        }

        relationships.entrySet().stream().forEach(r -> {
            sb.append(writer.relationshipToJdl(r.getKey(), r.getValue()));
            sb.append(LB).append(LB);
        });
        return sb.toString();
    }


}
