package com.github.aguilasa.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.velocity.util.StringUtils;

import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.jhipster.types.EntityField;
import com.github.aguilasa.jhipster.types.FieldType;
import com.github.aguilasa.metadata.Column;
import com.github.aguilasa.metadata.ColumnType;
import com.github.aguilasa.metadata.PrimaryKey;
import com.github.aguilasa.metadata.Table;

public class Converter {

	private static final Map<ColumnType, FieldType> COLUMN_TYPE_FIELD_TYPES_MAP = new LinkedHashMap<>();

	static public String lowerFirstLetter(String data) {
		String firstLetter = data.substring(0, 1).toLowerCase();
		String restLetters = data.substring(1);
		return firstLetter + restLetters;
	}

	public static String normalizeName(String name) {
		String[] split = name.split("_");
		if (split.length > 1) {
			StringBuilder sb = new StringBuilder();
			for (String s : split) {
				sb.append(StringUtils.capitalizeFirstLetter(s));
			}
			return sb.toString();
		}
		return StringUtils.capitalizeFirstLetter(name);
	}

	public static String normalizeFieldName(String name) {
		String[] split = name.split("_");
		if (split.length > 1) {
			StringBuilder sb = new StringBuilder();
			for (String s : split) {
				sb.append(StringUtils.capitalizeFirstLetter(s));
			}
			name = sb.toString();
		}
		return lowerFirstLetter(name);
	}

	public static Entity tableToEntity(Table table) {
		Entity entity = new Entity(normalizeName(table.getName()));
		entity.setTableName(table.getName());
		Set<Column> columns = table.getColumns();
		for (Column column : columns) {
			if (checkCreateField(table, column)) {
				EntityField entityField = columnToEntityField(column);
				entity.addField(entityField);
			}
		}
		return entity;
	}

	private static boolean checkCreateField(Table table, Column column) {
		PrimaryKey primaryKey = table.getPrimaryKey();
		if (primaryKey.getColumns().size() == 1 && primaryKey.existsColumnByName(column.getName())) {
			return false;
		}
		return !table.existsForeignKeyByColumnName(column.getName());
	}

	public static EntityField columnToEntityField(Column column) {
		EntityField field = new EntityField();
		field.setName(normalizeFieldName(column.getName()));
		field.setType(fieldTypeFromColumn(column));
		return field;
	}

	public static FieldType fieldTypeFromColumn(Column column) {
		ColumnType columnType = column.getType();
		if (COLUMN_TYPE_FIELD_TYPES_MAP.containsKey(columnType)) {
			return COLUMN_TYPE_FIELD_TYPES_MAP.get(columnType);
		} else if (columnType.equals(ColumnType.DECIMAL) || columnType.equals(ColumnType.NUMERIC)) {
			int columnSize = column.getLength();
			if (column.getScale() >= 0) {
				if (columnSize <= 19) {
					return FieldType.DOUBLE;
				}
			} else {
				if (columnSize <= 10) {
					return FieldType.INTEGER;
				} else if (columnSize <= 19) {
					return FieldType.LONG;
				}
			}
			return FieldType.BIGDECIMAL;
		}
		return null;
	}

	static {
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BIGINT, FieldType.LONG);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BLOB, FieldType.BLOB);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.CLOB, FieldType.TEXTBLOB);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.DATE, FieldType.LOCALDATE);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.IMAGE, FieldType.IMAGEBLOB);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.INTEGER, FieldType.INTEGER);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.DOUBLE, FieldType.DOUBLE);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.NVARCHAR, FieldType.STRING);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.SMALLINT, FieldType.INTEGER);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TIME, FieldType.DURATION);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TIMESTAMP, FieldType.INSTANT);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.VARCHAR, FieldType.STRING);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.CHAR, FieldType.STRING);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.NCHAR, FieldType.STRING);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TINYINT, FieldType.INTEGER);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.UUID, FieldType.UUID);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.JSONB, FieldType.TEXTBLOB);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.TEXT, FieldType.TEXTBLOB);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.XML, FieldType.TEXTBLOB);
		COLUMN_TYPE_FIELD_TYPES_MAP.put(ColumnType.BIT, FieldType.BOOLEAN);
	}

}
