package com.github.aguilasa.jhipster.types;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relationship {

    private Entity fromEntity;
    private String fromName;
    private Entity toEntity;
    private String toName;
    private RelationshipType relationshipType;

}
