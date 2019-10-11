package com.github.aguilasa.metadata;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.*;
import org.apache.commons.lang.StringUtils;

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

    public ForeignKey addForeignKey(Column column, Column referenceColumn) {
        return addForeignKey(new ForeignKey(column, referenceColumn));
    }

    public ForeignKey addForeignKey(ForeignKey foreignKey) {
        this.foreignKeys.add(foreignKey);
        return foreignKey;
    }

    public Column findColumnByName(String columnName) {
        return this.columns.stream() //
                .filter(c -> c.getName().equalsIgnoreCase(columnName)) //
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Coluna '%s' não encontrada.", columnName)));
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

        return String.format("TABLE %s (\r\n\t%s\r\n%s%s)", name, fields, pk, fk);
    }

    private String foreignKeysToString() {
        if (!foreignKeys.isEmpty()) {
            return foreignKeys.stream().map(ForeignKey::toString).collect(Collectors.joining("\r\n\t")).trim();
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
