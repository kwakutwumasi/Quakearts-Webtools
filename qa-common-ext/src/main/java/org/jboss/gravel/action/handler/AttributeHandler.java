package org.jboss.gravel.action.handler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.handler.NonCollectionHandler;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagHandler;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
@TldTag (
    name = "attribute",
    description = "Set the value of the given attribute on the enclosing component, to be evaluated as EL " +
        "only during the RENDER_RESPONSE phase.  Implemented by simply updating the attributes map of the " +
        "component every time the tag is evaluated (while the view is being [re]built).",
    attributes = {
        @TldAttribute (
            name = "name",
            description = "The name of the attribute to set.",
            required = true
        ),
        @TldAttribute (
            name = "value",
            description = "The value of the attribute to set.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "type",
            description = "The primitive type or class name of the attribute.  Use if conversion fails.",
            type = String.class
        )
    }
)
public final class AttributeHandler extends TagHandler implements NonCollectionHandler {

    private final TagAttribute nameAttribute;
    private final TagAttribute valueAttribute;
    private final TagAttribute typeAttribute;

    private static final Map<String, Class<?>> primitives;

    static {
        final Map<String, Class<?>> map = new HashMap<String, Class<?>>();
        map.put("byte", byte.class);
        map.put("short", short.class);
        map.put("int", int.class);
        map.put("long", long.class);
        map.put("char", char.class);
        map.put("boolean", boolean.class);
        map.put("float", float.class);
        map.put("double", double.class);
        primitives = Collections.unmodifiableMap(map); 
    }

    public AttributeHandler(final TagConfig config) {
        super(config);
        nameAttribute = getRequiredAttribute("name");
        valueAttribute = getRequiredAttribute("value");
        typeAttribute = getAttribute("type");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            final Map<String,Object> attributes = parent.getAttributes();
            final Class<?> targetType;
            if (typeAttribute != null) {
                final String typeName = typeAttribute.getValue();
                if (primitives.containsKey(typeName)) {
                    targetType = primitives.get(typeName);
                } else {
                    try {
                        targetType = Class.forName(typeName);
                    } catch (ClassNotFoundException e) {
                        final TagException tagException = new TagException(tag, "Value given for 'type' attribute is not a valid class: " + e.getMessage());
                        tagException.setStackTrace(e.getStackTrace());
                        throw tagException;
                    }
                }
            } else {
                targetType = Object.class;
            }
            final ValueExpression nameExpression = nameAttribute.getValueExpression(ctx, String.class);
            final ValueExpression valueExpression = valueAttribute.getValueExpression(ctx, targetType);
            final Object name = nameExpression.getValue(ctx);
            final Object value = valueExpression.getValue(ctx);
            if (log.isLoggable(Level.FINE)) {
                log.fine("Mapping attribute '" + name + "' to value '" + value + "' (type " + (value == null ? "null" : value.getClass().getName()) + ") for component " + parent);
            }
            attributes.put(String.valueOf(name), value);
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.action.handler.AttributeHandler");
}
