package com.github.aguilasa.generators;

public enum TemplatesPath {
	entity("templates/entity.vm"), //
	relationship("templates/relationship.vm");

	private String path;

	private TemplatesPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
