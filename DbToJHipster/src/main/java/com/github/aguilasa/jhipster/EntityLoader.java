package com.github.aguilasa.jhipster;

import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.metadata.MetaDataLoader;
import com.github.aguilasa.metadata.Table;
import com.github.aguilasa.utils.Converter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class EntityLoader {

    @NonNull
    private MetaDataLoader metaDataLoader;

    private Set<Entity> entities = new LinkedHashSet<>();

    public void loadAll() throws SQLException {
        entities.clear();
        metaDataLoader.loadTables();
        Set<Table> tables = metaDataLoader.getTables();
        for (Table table : tables) {
            entities.add(Converter.tableToEntity(table));
        }
    }

    public Entity findEntityByName(String entityName) {
        return entities.stream() //
        .filter(e -> e.getName().equalsIgnoreCase(entityName)) //
        .findFirst() //
        .orElseThrow(() -> new RuntimeException(String.format("Entidade '%s' não encontrada.", entityName)));
    }


}
