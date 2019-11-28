package com.github.aguilasa.metadata;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ForeignKey extends Constraint {

	private String referenceTable;

	private List<ForeignKeyColumn> columns = new LinkedList<>();

	public ForeignKey() {

	}

	public ForeignKey(String referenceTable) {
		this.referenceTable = referenceTable;
	}

	public String getReferenceTable() {
		return referenceTable;
	}

	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
	}

	public List<ForeignKeyColumn> getColumns() {
		return columns;
	}

	public void addColumn(ForeignKeyColumn column) {
		columns.add(column);
	}

	@Override
	public String toString() {
		if (!columns.isEmpty()) {
			String fields = columns.stream().map(c -> c.getColumn().getName()).collect(Collectors.joining(", ")).trim();
			String referenceFields = columns.stream().map(c -> c.getReferenceColumn().getName())
					.collect(Collectors.joining(", ")).trim();
			return String.format("CONSTRAINT %s FOREIGN KEY (%s) REFERENCES %s(%s)", getName(), fields, referenceTable,
					referenceFields);
		}
		return "";
	}

}
