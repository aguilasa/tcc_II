package com.github.aguilasa.jhipster.types;

import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Entity {

    @NonNull
    private String name;

    @Setter(AccessLevel.NONE)
    private List<EntityField> fields = new LinkedList<>();

    public void addField(EntityField field) {
        fields.add(field);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return name.equals(entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
