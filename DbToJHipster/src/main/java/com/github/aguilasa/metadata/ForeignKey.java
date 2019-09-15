package com.github.aguilasa.metadata;

import lombok.Getter;

public class ForeignKey extends Constraint {

	@Getter
	private Column column;
	@Getter
	private Column referenceColumn;

	public void setColumn(Column column) {
		checkOwner(column);
		checkColumnOwner(column);
		this.column = column;
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
			throw new RuntimeException(String.format("Erro ao adicionar o campo%s '%s' na chave estrangeira, pois não foi atribuída uma tabela para esta chave.", reference, checkedColumn.getName()));
		}
	}

	private void checkColumnOwner(Column checkedColumn) {
		if (checkedColumn.getOwner() == null) {
			throw new RuntimeException(String.format("Erro ao adicionar o campo '%s' na chave estrangeira da tabela '%', pois o mesmo não pertence a nenhuma tabela.", checkedColumn.getName(), getOwner().getName()));
		}
	}

}
