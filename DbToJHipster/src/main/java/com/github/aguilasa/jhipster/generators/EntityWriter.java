package com.github.aguilasa.jhipster.generators;

import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.jhipster.types.EntityField;
import com.github.aguilasa.metadata.Table;
import com.github.aguilasa.utils.Converter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EntityWriter {

    private VelocityEngine engine;
    private Template template;

    public EntityWriter() {
        engine = new VelocityEngine();
        engine.init();
        template = engine.getTemplate("src/main/resources/templates/entity.vm");
    }

    public String tableToJHipsterEntity(Table table) {
        Entity entity = Converter.tableToEntity(table);
        VelocityContext context = new VelocityContext();
        context.put("entityname", entity.getName());
        List<EntityField> fields = entity.getFields().stream().collect(Collectors.toList());
        context.put("fields", fields);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }
}