package org.jboss.gravel.data.handler;

import java.io.IOException;

import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.data.action.ReverseActionListener;

import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagHandler;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;

/**
 *
 */
@TldTag (
    name = "reverse",
    description = "Reverse a collection or array and store the result into an EL expression.",
    attributes = {
        @TldAttribute (
            name = "source",
            description = "An EL expression that contains the data set to be reversed.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "target",
            description = "An EL expression into which the reversed data set will be stored.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "if",
            description = "An EL expression which determines whether to execute the reverse.",
            required = false,
            deferredType = boolean.class
        )
    }
)
public final class ReverseHandler extends TagHandler {

    private final TagAttribute sourceAttribute;
    private final TagAttribute targetAttribute;
    private final TagAttribute ifAttribute;

    public ReverseHandler(final TagConfig config) {
        super(config);
        sourceAttribute = getRequiredAttribute("source");
        targetAttribute = getRequiredAttribute("target");
        ifAttribute = getAttribute("if");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (ComponentHandler.isNew(parent)) {
                if (! (parent instanceof ActionSource)) {
                    throw new TagException(tag, "Parent component is not an ActionSource");
                }
                ((ActionSource) parent).addActionListener(
                    new ReverseActionListener(
                        sourceAttribute.getValueExpression(ctx, Object.class),
                        targetAttribute.getValueExpression(ctx, Object.class),
                        ifAttribute == null ? null : ifAttribute.getValueExpression(ctx, Boolean.class)
                    )
                );
            }
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }
}
