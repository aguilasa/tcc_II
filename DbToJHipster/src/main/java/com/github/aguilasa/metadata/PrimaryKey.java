package com.github.aguilasa.metadata;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;

public class PrimaryKey extends Constraint {

	@Getter
	private Set<PrimaryKeyColumn> columns = new LinkedHashSet<>();

	public PrimaryKey(Table owner) {
		this.setOwner(owner);
	}

	public void addColumn(Column column, int keyPosition) {
		checkOwner(column);
		checkColumnOwner(column);
		columns.add(new PrimaryKeyColumn(column, keyPosition));
	}

	private void checkOwner(Column column) {
		if (getOwner() == null) {
			throw new RuntimeException(String.format("Erro ao adicionar o campo '%s' na chave primária, pois não foi atribuída uma tabela a esta chave.", column.getName()));
		}
	}

	private void checkColumnOwner(Column column) {
		if (!this.getOwner().equals(column.getOwner())) {
			throw new RuntimeException(String.format("Erro ao adicionar o campo '%s' na chave primária da tabela '%', pois são de tabelas diferentes.", column.getName(), getOwner().getName()));
		}
	}

}
