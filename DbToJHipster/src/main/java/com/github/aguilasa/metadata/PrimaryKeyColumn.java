package com.github.aguilasa.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class PrimaryKeyColumn {

    private Column column;
    private int keyPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryKeyColumn that = (PrimaryKeyColumn) o;
        return column.equals(that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column);
    }

}
