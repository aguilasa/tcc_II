package com.github.aguilasa.jhipster.types;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Entity {

	private String name;

	private String tableName;

	private List<Relationship> relationships = new LinkedList<>();

	public Entity() {

	}

	public Entity(String name) {
		this.name = name;
	}

	private List<EntityField> fields = new LinkedList<>();

	public void addField(EntityField field) {
		fields.add(field);
	}

	public void addRelationship(Relationship relationship) {
		relationships.add(relationship);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public List<EntityField> getFields() {
		return fields;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Entity entity = (Entity) o;
		return name.equals(entity.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
