package com.github.aguilasa.metadata;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
public class Table {

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    private Set<Column> columns = new LinkedHashSet<>();

    @Getter
    private PrimaryKey primaryKey = new PrimaryKey(this);

    @Getter
    private List<ForeignKey> foreignKeys = new LinkedList<>();

    public void addColumn(Column column) {
        column.setOwner(this);
        this.columns.add(column);
    }

    public void addPrimaryKey(String columnName, int keyPosition) {
        Column column = findColumnByName(columnName);
        primaryKey.addColumn(column, keyPosition);
    }

    public void addForeignKey(Column column, Column referenceColumn) {
        addForeignKey(new ForeignKey(column, referenceColumn));
    }

    public void addForeignKey(ForeignKey foreignKey) {
        this.foreignKeys.add(foreignKey);
    }

    public Column findColumnByName(String columnName) {
        return this.columns.stream() //
                .filter(c -> c.getName().equalsIgnoreCase(columnName)) //
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Coluna '%s' não encontrada.", columnName)));
    }

    @Override
    public String toString() {
        String fields = columns.stream().map(Column::toString).collect(Collectors.joining("\r\n\t")).trim();
        return String.format("TABLE %s (\r\n\t%s\r\n)", name, fields);
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
