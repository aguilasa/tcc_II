package com.github.aguilasa.metadata;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
public class ForeignKey extends Constraint {

	@NonNull
	@Setter
	@Getter
	private String referenceTable;

	@Getter
	private List<ForeignKeyColumn> columns = new LinkedList<>();

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
