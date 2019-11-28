package com.github.aguilasa.metadata;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

public class Table {

	private String name;

	private Set<Column> columns = new LinkedHashSet<>();

	private PrimaryKey primaryKey = new PrimaryKey(this);

	private Map<String, ForeignKey> foreignKeys = new LinkedHashMap<>();

	private List<UniqueConstraint> uniqueConstraints = new LinkedList<>();

	public Table() {

	}

	public Table(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Column> getColumns() {
		return columns;
	}

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public Map<String, ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public List<UniqueConstraint> getUniqueConstraints() {
		return uniqueConstraints;
	}

	public void addColumn(Column column) {
		column.setOwner(this);
		this.columns.add(column);
	}

	public void addPrimaryKey(String columnName, int keyPosition) {
		Column column = findColumnByName(columnName);
		primaryKey.addColumn(column, keyPosition);
	}

	private ForeignKey getForeignKey(String name, String referenceTable) {
		if (!foreignKeys.containsKey(name)) {
			ForeignKey foreignKey = new ForeignKey();
			foreignKey.setName(name);
			foreignKey.setReferenceTable(referenceTable);
			foreignKey.setOwner(this);
			foreignKeys.put(name, foreignKey);
		}
		return foreignKeys.get(name);
	}

	public ForeignKey addForeignKey(String name, String referenceTable, Column column, Column referenceColumn) {
		ForeignKey foreignKey = getForeignKey(name, referenceTable);
		foreignKey.addColumn(new ForeignKeyColumn(column, referenceColumn));
		return foreignKey;
	}

	public UniqueConstraint addUniqueConstraint(Column column) {
		if (!primaryKey.existsColumnByName(column.getName())) {
			UniqueConstraint uniqueConstraint = new UniqueConstraint(this);
			uniqueConstraint.setColumn(column);
			return addUniqueConstraint(uniqueConstraint);
		}
		return null;
	}

	public UniqueConstraint addUniqueConstraint(UniqueConstraint uniqueConstraint) {
		this.uniqueConstraints.add(uniqueConstraint);
		return uniqueConstraint;
	}

	public Column findColumnByName(String columnName) {
		return this.columns.stream() //
				.filter(c -> c.getName().equalsIgnoreCase(columnName)) //
				.findFirst()
				.orElseThrow(() -> new RuntimeException(String.format("Coluna '%s' não encontrada.", columnName)));
	}

	public boolean existsForeignKeyByColumnName(String columnName) {
		return foreignKeys.entrySet().stream().anyMatch(f -> {
			return f.getValue().getColumns().stream()
					.anyMatch(c -> c.getColumn().getName().equalsIgnoreCase(columnName));
		});
	}

	@Override
	public String toString() {
		String fields = columns.stream().map(Column::toString).collect(Collectors.joining("\r\n\t")).trim();
		String pk = primaryKey.toString();
		if (StringUtils.isNotEmpty(pk)) {
			pk = String.format("\t%s\r\n", pk);
		}

		String fk = foreignKeysToString();
		if (StringUtils.isNotEmpty(fk)) {
			fk = String.format("\t%s\r\n", fk);
		}

		String un = uniqueConstraintsToString();
		if (StringUtils.isNotEmpty(un)) {
			un = String.format("\t%s\r\n", un);
		}

		return String.format("TABLE %s (\r\n\t%s\r\n%s%s%s)", name, fields, pk, fk, un);
	}

	private String foreignKeysToString() {
		if (!foreignKeys.isEmpty()) {
			return foreignKeys.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()).stream()
					.map(ForeignKey::toString).collect(Collectors.joining("\r\n\t")).trim();
		}
		return "";
	}

	private String uniqueConstraintsToString() {
		if (!uniqueConstraints.isEmpty()) {
			return uniqueConstraints.stream().map(UniqueConstraint::toString).collect(Collectors.joining("\r\n\t"))
					.trim();
		}
		return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
