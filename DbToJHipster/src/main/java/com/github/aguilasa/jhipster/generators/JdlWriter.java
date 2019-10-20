package com.github.aguilasa.jhipster.generators;

import com.github.aguilasa.jhipster.types.Entity;
import com.github.aguilasa.jhipster.types.EntityField;
import com.github.aguilasa.jhipster.types.Relationship;
import com.github.aguilasa.jhipster.types.RelationshipType;
import com.github.aguilasa.metadata.Table;
import com.github.aguilasa.utils.Converter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JdlWriter {

    private VelocityEngine engine;
    private Template template = null;
    private TemplatesPath path = null;

    public JdlWriter() {
        engine = new VelocityEngine();
        engine.init();
    }

    public String tableToJHipsterEntity(Table table) {
        Entity entity = Converter.tableToEntity(table);
        return entityToJdl(entity);
    }

    public String entityToJdl(Entity entity) {
        VelocityContext context = new VelocityContext();
        context.put("name", entity.getName());
        List<EntityField> fields = entity.getFields().stream().collect(Collectors.toList());
        context.put("fields", fields);
        StringWriter writer = new StringWriter();
        getTemplate(TemplatesPath.entity).merge(context, writer);
        return writer.toString();
    }

    public String relationshipToJdl(RelationshipType relationshipType, List<Relationship> relationships) {
        VelocityContext context = new VelocityContext();
        context.put("name", relationshipType.getName());
        context.put("relationships", relationships);
        StringWriter writer = new StringWriter();
        getTemplate(TemplatesPath.relationship).merge(context, writer);
        return writer.toString();
    }

    private Template getTemplate(TemplatesPath path) {
        if (template == null || this.path != path) {
            template = engine.getTemplate(path.getPath());
            this.path = path;
        }
        return template;
    }

}
