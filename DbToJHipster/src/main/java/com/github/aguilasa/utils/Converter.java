package com.github.aguilasa.utils;

import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.jhipster.types.EntityField;
import com.github.aguilasa.jhipster.types.FieldType;
import com.github.aguilasa.metadata.Column;
import com.github.aguilasa.metadata.ColumnType;
import com.github.aguilasa.metadata.Table;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Converter {

    private static final Map<ColumnType, FieldType> COLUMN_TYPE_FIELD_TYPES_MAP = new LinkedHashMap<>();

    public static Entity tableToEntity(Table table) {
        Entity entity = new Entity(table.getName());
        Set<Column> columns = table.getColumns();
        for(Column column : columns) {
            EntityField entityField = columnToEntityField(column);
            entity.addField(entityField);
        }
        return entity;
    }

    public static EntityField columnToEntityField(Column column) {
        EntityField field = new EntityField();
        field.setName(column.getName());
        field.setType(columnTypeToFieldType(column.getType()));
        return field;
    }

    public static FieldType columnTypeToFieldType(ColumnType columnType) {
        if (COLUMN_TYPE_FIELD_TYPES_MAP.containsKey(columnType)) {
           return COLUMN_TYPE_FIELD_TYPES_MAP.get(columnType);
        }
        return FieldType.STRING;
    }

    static {
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BIGINT, FieldType.LONG);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BIGINT_IDENTITY, FieldType.LONG);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BIGSERIAL, FieldType.LONG);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BLOB, FieldType.BLOB);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BOOL, FieldType.BOOLEAN);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BPCHAR, FieldType.STRING);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BYTEA, FieldType.INTEGER);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.CLOB, FieldType.TEXTBLOB);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.DATE, FieldType.LOCALDATE);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.DATETIME, FieldType.LOCALDATE);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.FLOAT8, FieldType.DOUBLE);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.IMAGE, FieldType.IMAGEBLOB);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.INT, FieldType.INTEGER);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.INT_IDENTITY, FieldType.INTEGER);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.INT2, FieldType.INTEGER);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.INT4, FieldType.INTEGER);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.INT8, FieldType.LONG);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.JSONB, FieldType.BLOB);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.NVARCHAR, FieldType.STRING);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.SERIAL, FieldType.INTEGER);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.SMALLINT, FieldType.INTEGER);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TEXT, FieldType.TEXTBLOB);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TIME, FieldType.DURATION);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TIMESTAMP, FieldType.INSTANT);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TIMESTAMPTZ, FieldType.INSTANT);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.UUID, FieldType.UUID);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.VARCHAR, FieldType.STRING);
        COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.VARCHAR2, FieldType.STRING);
    }

}
