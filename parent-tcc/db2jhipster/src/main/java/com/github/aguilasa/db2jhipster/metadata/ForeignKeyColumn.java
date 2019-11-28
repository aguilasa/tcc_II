package com.github.aguilasa.db2jhipster.metadata;

public class ForeignKeyColumn {

	private Table owner;
	private String name;
	private Column column;
	private Column referenceColumn;

	public Table getOwner() {
		return owner;
	}

	public void setOwner(Table owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ForeignKeyColumn(Column column, Column referenceColumn) {
		this.column = column;
		this.referenceColumn = referenceColumn;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		checkOwner(column);
		checkColumnOwner(column);
		this.column = column;
	}

	public Column getReferenceColumn() {
		return referenceColumn;
	}

	public void setReferenceColumn(Column referenceColumn) {
		checkOwner(referenceColumn, true);
		this.referenceColumn = referenceColumn;
	}

	private void checkOwner(Column checkedColumn) {
		checkOwner(checkedColumn, false);
	}

	private void checkOwner(Column checkedColumn, boolean isReference) {
		if (getOwner() == null) {
			String reference = isReference ? " referência" : "";
			throw new RuntimeException(String.format(
					"Erro ao adicionar o campo%s '%s' na chave estrangeira, pois não foi atribuída uma tabela para esta chave.",
					reference, checkedColumn.getName()));
		}
	}

	private void checkColumnOwner(Column checkedColumn) {
		if (checkedColumn.getOwner() == null) {
			throw new RuntimeException(String.format(
					"Erro ao adicionar o campo '%s' na chave estrangeira da tabela '%', pois o mesmo não pertence a nenhuma tabela.",
					checkedColumn.getName(), getOwner().getName()));
		}
	}

}
