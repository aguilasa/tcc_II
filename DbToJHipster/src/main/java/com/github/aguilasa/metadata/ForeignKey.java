package com.github.aguilasa.metadata;

import lombok.Getter;

public class ForeignKey extends Constraint {

	@Getter
	private Column column;
	@Getter
	private Column referenceColumn;

	public void setColumn(Column column) {
		checkOwner(column);
		this.column = column;
	}

	public void setReferenceColumn(Column referenceColumn) {
		this.referenceColumn = referenceColumn;
	}

	private void checkOwner(Column column) {
		checkOwner(column, false);
	}

	private void checkOwner(Column column, boolean isReference) {
		if (getOwner() == null) {
			String reference = isReference ? " referência" : "";
			throw new RuntimeException(String.format("Erro ao adicionar o campo%s '%s' na chave estrangeira, pois não foi atribuída uma tabela para esta chave.", reference, column.getName()));
		}
	}

}
