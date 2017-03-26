package org.jboss.gravel.data.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.util.DelimitedStringList;
import org.jboss.gravel.common.util.NodeHashMap;
import org.jboss.gravel.common.util.NodeMap;
import org.jboss.gravel.data.ui.UIProperties;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import java.util.logging.Logger;

/**
 *
 */
@TldTag (
    name = "loadProperties",
    description = "Load properties from a properties file.",
    attributes = {
        @TldAttribute (
            name = "path",
            description = "The path of the properties file.  If the given path is relative, it will be relative to the " +
                "<code>/WEB-INF/</code> folder in the WAR deployment.",
            required = true
        ),
        @TldAttribute (
            name = "var",
            description = "The name of the request-scope attribute to bind the properties map to.",
            required = true
        ),
        @TldAttribute(
            name = "charset",
            description = "The character set encoding of the properties file.  If not given, UTF-8 is assumed."
        )
    }
)
public final class LoadPropertiesHandler extends ComponentHandler {
    private final TagAttribute pathAttribute;
    private final TagAttribute varAttribute;
    private final TagAttribute charsetAttribute;

    public LoadPropertiesHandler(final ComponentConfig config) {
        super(config);
        pathAttribute = getRequiredAttribute("path");
        varAttribute = getRequiredAttribute("var");
        charsetAttribute = getAttribute("charset");
    }

    public void onComponentCreated(FaceletContext ctx, UIComponent c, UIComponent parent) {
        try {
            final FacesContext facesContext = ctx.getFacesContext();
            final Object pathValue = pathAttribute.getValueExpression(ctx, String.class).getValue(ctx);
            final String path;
            if (pathValue == null) {
                throw new FacesException("Value for properties file path is null");
            }
            if (! pathValue.toString().startsWith("/")) {
                path = "/WEB-INF/" + pathValue;
            } else {
                path = pathValue.toString();
            }
            @SuppressWarnings("unused")
			final String charsetName;
            if (charsetAttribute == null) {
                charsetName = "UTF-8";
            } else {
                final Object encodingValue = charsetAttribute.getValueExpression(ctx, String.class).getValue(ctx);
                if (encodingValue == null) {
                    charsetName = "UTF-8";
                } else {
                    charsetName = encodingValue.toString();
                }
            }
            final ExternalContext externalContext = facesContext.getExternalContext();
            final Properties properties;
            final InputStream stream = externalContext.getResourceAsStream(path);
            try {
                properties = new Properties();
                try {
                    properties.load(stream);
                } catch (IOException e) {
                    final FacesException ex = new FacesException("Unable to load properties: " + e.getMessage());
                    ex.setStackTrace(e.getStackTrace());
                    throw ex;
                }
            } finally {
                try {
                    stream.close();
                } catch (IOException e) {
                    log.warning("Failed to close resource stream: " + e.getMessage());
                }
            }
            final NodeMap nodeMap = new NodeHashMap();
            for (Map.Entry<Object,Object> entry : properties.entrySet()) {
                NodeMap loc = nodeMap;
                for (String part : new DelimitedStringList(entry.getKey().toString(), '.')) {
                    if (loc.containsKey(part)) {
                        loc = loc.get(part);
                    } else {
                        final NodeMap newMap = new NodeHashMap();
                        loc.put(part, newMap);
                        loc = newMap;
                    }
                }
                loc.setNodeValue(entry.getValue().toString());
            }
            nodeMap.lock();
            final UIProperties uiProperties = ((UIProperties) c);
            uiProperties.setPropertiesMap(nodeMap);
            final Object varValue = varAttribute.getValueExpression(ctx, String.class).getValue(ctx);
            if (varValue == null) {
                throw new TagException(tag, "Value of 'var' attribute cannot be null");
            }
            uiProperties.setVar(varValue.toString());
            uiProperties.updatePropertyMap(facesContext);
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.data.handler.LoadPropertiesHandler");
}
