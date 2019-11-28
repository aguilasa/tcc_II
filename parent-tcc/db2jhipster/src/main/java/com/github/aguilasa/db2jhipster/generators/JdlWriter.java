package com.github.aguilasa.db2jhipster.generators;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.github.aguilasa.db2jhipster.jhipster.Entity;
import com.github.aguilasa.db2jhipster.jhipster.EntityField;
import com.github.aguilasa.db2jhipster.jhipster.Relationship;
import com.github.aguilasa.db2jhipster.jhipster.RelationshipType;
import com.github.aguilasa.db2jhipster.metadata.Table;

public class JdlWriter {

    private VelocityEngine engine;
    private Template template = null;
    private TemplatesPath path = null;

    public JdlWriter() {
        engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
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
