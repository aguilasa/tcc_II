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
	BIGSERIAL("bigserial"), //
	FLOAT8("float8"), //
	TIMESTAMPTZ("timestamptz"), //
	INT2("int2"), //
	TIME("time"), //
	BPCHAR("bpchar"), //
	BYTEA("bytea"), //
	BIGINT("bigint"), //
	DATETIME("datetime"), //
	BIGINT_IDENTITY("bigint identity"), //
	SMALLINT("smallint"), //
	INT("int"), //
	IMAGE("image"), //
	INT_IDENTITY("int identity"), //
	NVARCHAR("nvarchar"), //
	VARCHAR2("varchar2"), //
	NUMBER("number"), //
	CLOB("clob"), //
	BLOB("blob");

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

	@Override
	public String toString() {
		return value;
	}
}
