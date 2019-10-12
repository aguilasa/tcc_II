package com.github.aguilasa.jhipster.types;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

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

}
