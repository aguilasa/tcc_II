package com.github.aguilasa.metadata;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;

public class PrimaryKey extends Constraint {

	@Getter
	private Set<Column> columns = new LinkedHashSet<>();

	public void addColumn(Column column) {
		checkOwner(column);
		checkColumnOwner(column);
		columns.add(column);
	}

	private void checkOwner(Column column) {
		if (getOwner() == null) {
			throw new RuntimeException(String.format("Erro ao adicionar o campo '%s' na chave prim�ria, pois n�o foi atribu�da uma tabela a esta chave.", column.getName()));
		}
	}

	private void checkColumnOwner(Column column) {
		if (!this.getOwner().equals(column.getOwner())) {
			throw new RuntimeException(String.format("Erro ao adicionar o campo '%s' na chave prim�ria da tabela '%', pois s�o de tabelas diferentes.", column.getName(), getOwner().getName()));
		}
	}

}
