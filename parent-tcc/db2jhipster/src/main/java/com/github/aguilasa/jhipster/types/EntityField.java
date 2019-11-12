package com.github.aguilasa.jhipster.types;

import lombok.*;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class EntityField {

    @NonNull
    private String name;
    @NonNull
    private FieldType type;
    @Setter(AccessLevel.NONE)
    private String validations;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, String> validationsValues = new LinkedHashMap<>();

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
