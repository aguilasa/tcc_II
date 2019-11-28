package com.github.aguilasa.db2jhipster.metadata;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PrimaryKey extends Constraint {

	private Set<PrimaryKeyColumn> columns = new LinkedHashSet<>();

	public PrimaryKey(Table owner) {
		this.setOwner(owner);
	}

	public Set<PrimaryKeyColumn> getColumns() {
		return columns;
	}

	public void addColumn(Column column, int keyPosition) {
		checkOwner(column);
		checkColumnOwner(column);
		columns.add(new PrimaryKeyColumn(column, keyPosition));
	}

	public boolean existsColumnByName(String columnName) {
		return columns.stream().anyMatch(c -> c.getColumn().getName().equalsIgnoreCase(columnName));
	}

	private void checkOwner(Column column) {
		if (getOwner() == null) {
			throw new RuntimeException(String.format(
					"Erro ao adicionar o campo '%s' na chave primária, pois não foi atribuída uma tabela a esta chave.",
					column.getName()));
		}
	}

	private void checkColumnOwner(Column column) {
		if (!this.getOwner().equals(column.getOwner())) {
			throw new RuntimeException(String.format(
					"Erro ao adicionar o campo '%s' na chave primária da tabela '%', pois são de tabelas diferentes.",
					column.getName(), getOwner().getName()));
		}
	}

	@Override
	public String toString() {
		if (!columns.isEmpty()) {
			String fields = columns.stream().map(PrimaryKeyColumn::toString).collect(Collectors.joining(", ")).trim();
			return String.format("CONSTRAINT %s PRIMARY KEY (%s)", getName(), fields);
		}
		return "";
	}

}
