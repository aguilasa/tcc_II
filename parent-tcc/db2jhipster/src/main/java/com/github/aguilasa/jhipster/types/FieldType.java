package com.github.aguilasa.jhipster.types;

public enum FieldType {
	STRING ("String"), //
	INTEGER("Integer"), //
	LONG("Long"), //
	FLOAT("Float"), //
	DOUBLE("Double"), //
	BIGDECIMAL("BigDecimal"), //
	LOCALDATE("LocalDate"), //
	INSTANT("Instant"), //
	ZONEDDATETIME("ZonedDateTime"), //
	DURATION("Duration"), //
	UUID("UUID"), //
	BOOLEAN("Boolean"), //
	ENUMERATION("Enumeration"), //
	BLOB("Blob"), //
	TEXTBLOB("TextBlob"), //
	IMAGEBLOB("ImageBlob");

	private String value;

	private FieldType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
