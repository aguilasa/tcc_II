package com.github.aguilasa.db2jhipster.metadata;

import java.util.Objects;

public class PrimaryKeyColumn {

	private Column column;
	private int keyPosition;

	public PrimaryKeyColumn(Column column, int keyPosition) {
		this.column = column;
		this.keyPosition = keyPosition;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public int getKeyPosition() {
		return keyPosition;
	}

	public void setKeyPosition(int keyPosition) {
		this.keyPosition = keyPosition;
	}

	@Override
	public String toString() {
		return column.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PrimaryKeyColumn that = (PrimaryKeyColumn) o;
		return column.equals(that.column);
	}

	@Override
	public int hashCode() {
		return Objects.hash(column);
	}

}
