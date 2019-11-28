package com.github.aguilasa.db2jhipster.metadata;

public class UniqueConstraint extends Constraint {

	private Column column;

	public Column getColumn() {
		return column;
	}

	public UniqueConstraint(Table owner) {
		this.setOwner(owner);
	}

	public void setColumn(Column column) {
		checkOwner(column);
		checkColumnOwner(column);
		this.column = column;
	}

	private void checkOwner(Column column) {
		if (getOwner() == null) {
			throw new RuntimeException(String.format(
					"Erro ao adicionar o campo '%s' na constraint única, pois não foi atribuída uma tabela a esta chave.",
					column.getName()));
		}
	}

	private void checkColumnOwner(Column column) {
		if (!this.getOwner().equals(column.getOwner())) {
			throw new RuntimeException(String.format(
					"Erro ao adicionar o campo '%s' na constraint única da tabela '%', pois são de tabelas diferentes.",
					column.getName(), getOwner().getName()));
		}
	}

	@Override
	public String toString() {
		if (column != null) {
			return String.format("CONSTRAINT %s UNIQUE (%s)", getName(), column.getName());
		}
		return "";
	}

}
