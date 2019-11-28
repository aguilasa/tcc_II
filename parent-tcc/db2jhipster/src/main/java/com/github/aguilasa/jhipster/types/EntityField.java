package com.github.aguilasa.jhipster.types;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

public class EntityField {

	private String name;
	private FieldType type;
	private String validations;
	private Map<String, String> validationsValues = new LinkedHashMap<>();

	public EntityField() {

	}

	public EntityField(String name, FieldType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public String getValidations() {
		return validations;
	}

	public void addValidation(String name) {
		addValidation(name, "");
	}

	public void addValidation(String name, String value) {
		validationsValues.put(name, value);
		processValidations();
	}

	private void processValidations() {
		validations = validationsValues.entrySet().stream().map(e -> {
			if (StringUtils.isNotEmpty(e.getValue())) {
				return String.format("%s(%s)", e.getKey(), e.getValue());
			}
			return e.getKey();
		}).collect(Collectors.joining(" "));
	}

}
