package com.github.aguilasa.metadata;

public enum ColumnType {

	UUID("uuid"), //
	VARCHAR("varchar"), //
	DATE("date"), //
	BOOL("bool"), //
	NUMERIC("numeric"), //
	INT4("int4"), //
	TIMESTAMP("timestamp"), //
	INT8("int8"), //
	TEXT("text"), //
	JSONB("jsonb"), //
	SERIAL("serial"), //
	BIGSERIAL("bigserial");

	private String value;

	private ColumnType(String value) {
		this.value = value;
	}

	public static ColumnType getEnum(String enumName) {
		ColumnType[] elements = values();
		for (ColumnType columnType : elements) {
			if (columnType.value.equalsIgnoreCase(enumName)) {
				return columnType;
			}
		}
		return null;
	}
}
