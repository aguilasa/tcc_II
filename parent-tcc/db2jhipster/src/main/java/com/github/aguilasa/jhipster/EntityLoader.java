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
import org.apache.velocity.util.StringUtils;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class EntityLoader {

	private static final int MTM_SIZE = 2;
	private static final String LB = "\r\n";
	private static final String[] NAMES_SUFFIX = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine", "Ten" };

	@NonNull
	private DatabaseLoader databaseLoader;

	private Set<Entity> entities = new LinkedHashSet<>();
	private List<Relationship> relationships = new LinkedList<>();
	private Set<String> relationshipsNames = new LinkedHashSet<>();

	public void loadAll() throws SQLException {
		loadEntities();
		loadRelationships();
	}

	public void loadEntities() throws SQLException {
		loadEntities(true);
	}

	public void loadEntities(boolean loadMetadata) throws SQLException {
		try {
			entities.clear();
			if (loadMetadata) {
				databaseLoader.loadAll();
			}
			Set<Table> tables = databaseLoader.getTables();
			for (Table table : tables) {
				entities.add(Converter.tableToEntity(table));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadRelationships() {
		relationships.clear();
		Set<Entity> remove = new LinkedHashSet<>();
		for (Entity entity : entities) {
			Table table = databaseLoader.findTableByName(entity.getTableName());
			if (isManyToManyTable(table)) {
				addManyToManyRelationship(table, entity);
				remove.add(entity);
			} else {
				table.getForeignKeys().entrySet().stream().map(Map.Entry::getValue).forEach(f -> {
					addOneToOneRelationship(f, entity);
				});
			}
		}
		for (Entity entity : remove) {
			entities.remove(entity);
		}
	}

	private String relationshipName(String first, String second) {
		String name = String.format("%s%s", first, StringUtils.capitalizeFirstLetter(second));
		int index = 0;
		String tmp = name;
		while (relationshipsNames.contains(tmp)) {
			tmp = name.concat(NAMES_SUFFIX[index++]);
		}
		name = tmp;
		relationshipsNames.add(name);
		return name;
	}

	public Relationship createRelationshipFromColumn(Column fromColumn, Column toColumn,
			RelationshipType relationshipType) {
		Relationship relationship = new Relationship();
		Entity from = findEntityByTableName(fromColumn.getOwner().getName());
		Entity to = findEntityByTableName(toColumn.getOwner().getName());
		relationship.setFromEntity(from);
		String first = to.getName().toLowerCase();
		String second = from.getName().toLowerCase();
		relationship.setFromName(relationshipName(first, second));
		relationship.setToEntity(to);
		relationship.setToName(relationshipName(second, first));
		relationship.setRelationshipType(relationshipType);
		return relationship;
	}

	public Relationship createRelationshipFromForeignKey(ForeignKey foreignKey, RelationshipType relationshipType) {
		Relationship relationship = new Relationship();
		Entity from = findEntityByTableName(foreignKey.getOwner().getName());
		Entity to = findEntityByTableName(foreignKey.getReferenceTable());
		relationship.setFromEntity(from);
		String first = to.getName().toLowerCase();
		String second = from.getName().toLowerCase();
		relationship.setFromName(relationshipName(first, second));
		relationship.setToEntity(to);
		relationship.setToName(relationshipName(second, first));
		relationship.setRelationshipType(relationshipType);
		return relationship;
	}

	public void addOneToOneRelationship(ForeignKey foreignKey, Entity entity) {
		Relationship relationship = createRelationshipFromForeignKey(foreignKey, RelationshipType.OneToOne);
		entity.addRelationship(relationship);
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

	public void addManyToManyRelationship(Table table, Entity entity) {
		List<ForeignKey> foreignKeys = table.getForeignKeys().entrySet().stream().map(Map.Entry::getValue)
				.collect(Collectors.toList());
		Column fromColumn = foreignKeys.get(0).getColumns().get(0).getReferenceColumn();
		Column toColumn = foreignKeys.get(1).getColumns().get(0).getReferenceColumn();
		Relationship relationship = createRelationshipFromColumn(fromColumn, toColumn, RelationshipType.ManyToMany);
		entity.addRelationship(relationship);
		addRelationship(relationship);
	}

	private boolean isManyToManyTable(Table table) {
		int columns_length = table.getColumns().size();
		int foreign_length = table.getForeignKeys().size();
		int primary_length = table.getPrimaryKey().getColumns().size();
		return columns_length == MTM_SIZE && columns_length == foreign_length && columns_length == primary_length;
	}

	public Entity findEntityByTableName(String tableName) {
		return entities.stream() //
				.filter(e -> e.getTableName().equalsIgnoreCase(tableName)) //
				.findFirst() //
				.orElseThrow(() -> new RuntimeException(String.format("Entidade '%s' não encontrada.", tableName)));
	}

	private void addRelationship(Relationship relationship) {
		relationships.add(relationship);
	}

	public String toJdl() {
		JdlWriter writer = new JdlWriter();
		StringBuilder sb = new StringBuilder();
		for (Entity entity : entities) {
			sb.append(writer.entityToJdl(entity));
			sb.append(LB).append(LB);
		}

		Map<RelationshipType, List<Relationship>> collect = relationships.stream()
				.collect(Collectors.groupingBy(Relationship::getRelationshipType));

		collect.entrySet().stream().forEach(r -> {
			sb.append(writer.relationshipToJdl(r.getKey(), r.getValue()));
			sb.append(LB).append(LB);
		});
		return sb.toString();
	}

}
