package com.github.aguilasa.metadata;

public enum ColumnType {

	BIGINT("BIGINT"), //
	BIT("BIT"), //
	BLOB("BLOB"), //
	CHAR("CHAR"), //
	CLOB("CLOB"), //
	DATE("DATE"), //
	DECIMAL("DECIMAL"), //
	DOUBLE("DOUBLE"), //
	INTEGER("INTEGER"), //
	LONGNVARCHAR("LONGNVARCHAR"), //
	LONGVARBINARY("LONGVARBINARY"), //
	LONGVARCHAR("LONGVARCHAR"), //
	NCHAR("NCHAR"), //
	NUMERIC("NUMERIC"), //
	NVARCHAR("NVARCHAR"), //
	OTHER("OTHER"), //
	SMALLINT("SMALLINT"), //
	TIME("TIME"), //
	TIMESTAMP("TIMESTAMP"), //
	TINYINT("TINYINT"), //
	VARCHAR("VARCHAR"), //
	IMAGE("IMAGE"), //
	UUID("UUID"), //
	JSONB("JSONB"), //
	TEXT("TEXT"), //
	XML("XML"), //
	REAL("REAL");

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
