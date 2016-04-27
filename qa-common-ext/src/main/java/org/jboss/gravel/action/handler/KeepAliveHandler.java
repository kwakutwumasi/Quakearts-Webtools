package org.jboss.gravel.action.handler;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.facelets.TagHandler;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.FaceletContext;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.FacesException;
import javax.el.ELException;
import javax.el.ValueExpression;

import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
@TldTag (
    name = "keepAlive",
    description = "Store an EL expression into the current view scope.  The expression's value will be restored " +
        "after the view is rebuilt.",
    attributes = {
        @TldAttribute (
            name = "value",
            description = "The EL expression to save and restore with the view.",
            required = true,
            deferredType = Object.class
        )
    }
)
public final class KeepAliveHandler extends TagHandler {
    private final TagAttribute valueAttribute;

    public KeepAliveHandler(final TagConfig config) {
        super(config);
        valueAttribute = getRequiredAttribute("value");
    }

    @SuppressWarnings ({"unchecked"})
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            final UIViewRoot viewRoot = ctx.getFacesContext().getViewRoot();
            final Map<String,Object> viewRootMap = viewRoot.getAttributes();
            final Map<String,KeepAliveEntry> keepAliveList;
            if (viewRootMap.containsKey("org.jboss.gravel.KeepAliveList")) {
                keepAliveList = (Map<String,KeepAliveEntry>) viewRootMap.get("org.jboss.gravel.KeepAliveList");
            } else {
                keepAliveList = new HashMap<String, KeepAliveHandler.KeepAliveEntry>();
                viewRootMap.put("org.jboss.gravel.KeepAliveList", keepAliveList);
            }
            final ValueExpression valueExpression = valueAttribute.getValueExpression(ctx, Object.class);
            keepAliveList.put(valueExpression.getExpressionString(),new KeepAliveEntry(valueExpression, valueExpression.getValue(ctx)));
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    public static class KeepAliveEntry implements Serializable {
        private static final long serialVersionUID = 1L;

        private final ValueExpression valueExpression;
        private final Object value;

        public KeepAliveEntry(final ValueExpression valueExpression, final Object value) {
            this.valueExpression = valueExpression;
            this.value = value;
        }

        public ValueExpression getValueExpression() {
            return valueExpression;
        }

        public Object getValue() {
            return value;
        }
    }
}
