package com.github.aguilasa.db2jhipster.jhipster;

public class Relationship {

	private Entity fromEntity;
	private String fromName;
	private Entity toEntity;
	private String toName;
	private RelationshipType relationshipType;

	public Entity getFromEntity() {
		return fromEntity;
	}

	public void setFromEntity(Entity fromEntity) {
		this.fromEntity = fromEntity;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Entity getToEntity() {
		return toEntity;
	}

	public void setToEntity(Entity toEntity) {
		this.toEntity = toEntity;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public RelationshipType getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}

}
