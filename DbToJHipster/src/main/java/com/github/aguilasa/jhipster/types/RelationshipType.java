package com.github.aguilasa.jhipster.types;

public enum RelationshipType {
    ManyToMany("ManyToMany"),
    ManyToOne("ManyToOne"),
    OneToMany("OneToMany"),
    OneToOne("OneToOne");

    private String name;

    private RelationshipType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
