package org.jboss.gravel.data.handler;

import java.io.IOException;
import java.util.Comparator;

import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.data.action.SortActionListener;

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
    name = "sort",
    description = "Sort a collection or array and store the result into an EL expression.",
    attributes = {
        @TldAttribute (
            name = "source",
            description = "An EL expression that contains the data set to be sorted.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "target",
            description = "An EL expression into which the sorted data set will be stored.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "entryVar",
            description = "The variable name to hold the current entry.  This name is used by the argument expression."
        ),
        @TldAttribute (
            name = "comparator",
            description = "An EL expression which resolves to a comparator to use for this sort.",
            deferredType = Comparator.class
        ),
        @TldAttribute (
            name = "argument",
            description = "An EL expression whose value is used as an argument to the comparator function.",
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "if",
            description = "An EL expression which determines whether to execute the sort.",
            deferredType = boolean.class
        ),
        @TldAttribute (
            name = "reverse",
            description = "An EL expression which determines whether to reverse the direction of the sort.",
            deferredType = boolean.class
        )
    }
)
public final class SortHandler extends TagHandler {

    private final TagAttribute sourceAttribute;
    private final TagAttribute targetAttribute;
    private final TagAttribute entryVarNameAttribute;
    private final TagAttribute comparatorAttribute;
    private final TagAttribute argumentAttribute;
    private final TagAttribute ifAttribute;
    private final TagAttribute reverseAttribute;

    public SortHandler(final TagConfig config) {
        super(config);
        sourceAttribute = getRequiredAttribute("source");
        targetAttribute = getRequiredAttribute("target");
        entryVarNameAttribute = getAttribute("entryVar");
        comparatorAttribute = getAttribute("comparator");
        argumentAttribute = getAttribute("argument");
        ifAttribute = getAttribute("if");
        reverseAttribute = getAttribute("reverse");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (ComponentHandler.isNew(parent)) {
                if (! (parent instanceof ActionSource)) {
                    throw new TagException(tag, "Parent component is not an ActionSource");
                }
                ((ActionSource) parent).addActionListener(
                    new SortActionListener(
                        sourceAttribute.getValueExpression(ctx, Object.class),
                        targetAttribute.getValueExpression(ctx, Object.class),
                        entryVarNameAttribute == null ? null : (String) entryVarNameAttribute.getValueExpression(ctx, String.class).getValue(ctx),
                        comparatorAttribute == null ? null : comparatorAttribute.getValueExpression(ctx, Comparator.class),
                        argumentAttribute == null ? null : argumentAttribute.getValueExpression(ctx, Object.class),
                        ifAttribute == null ? null : ifAttribute.getValueExpression(ctx, Boolean.class),
                        reverseAttribute == null ? null : reverseAttribute.getValueExpression(ctx, Boolean.class)
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
