package com.github.aguilasa.jhipster.generators;

public enum TemplatesPath {
    entity("src/main/resources/templates/entity.vm"), //
    relationship("src/main/resources/templates/relationship.vm");

    private String path;

    private TemplatesPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    
    
}
