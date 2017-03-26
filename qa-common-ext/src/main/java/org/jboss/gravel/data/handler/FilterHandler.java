package org.jboss.gravel.data.handler;

import java.io.IOException;

import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.TagHandler;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.component.UIComponent;
import javax.faces.component.ActionSource;
import javax.faces.FacesException;
import javax.el.ELException;

import org.jboss.gravel.data.action.FilterActionListener;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.Filter;

/**
 *
 */
@TldTag (
    name = "filter",
    description = "Filter a collection, map, or array and store the result into an EL expression.",
    attributes = {
        @TldAttribute (
            name = "source",
            description = "An EL expression that contains the data set to be filtered.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "target",
            description = "An EL expression into which the filtered data set will be stored.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "entryVar",
            description = "The variable name to hold the current entry.  This name is used by the test expression.",
            required = true
        ),
        @TldAttribute (
            name = "test",
            description = "An EL expression which is applied to each entry in the data set.  If the result is " +
                "true, then the corresponding data item will be included in the target data set.",
            deferredType = boolean.class
        ),
        @TldAttribute (
            name = "filter",
            description = "An EL expresion which resolves to a Filter instance, which will be used to test the entries " +
                "in the data set.",
            deferredType = Filter.class
        ),
        @TldAttribute (
            name = "filterArgument",
            description = "An EL expression which resolves to a value that the filter will use to test.  If not given, " +
                "the current entry is used.",
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "if",
            description = "An EL expression which determines whether to execute this filter.",
            required = false,
            deferredType = boolean.class
        )
    }
)
public final class FilterHandler extends TagHandler {
    private final TagAttribute sourceTagAttribute;
    private final TagAttribute targetTagAttribute;
    private final TagAttribute entryVarTagAttribute;
    private final TagAttribute testTagAttribute;
    private final TagAttribute filterTagAttribute;
    private final TagAttribute filterArgumentTagAttribute;
    private final TagAttribute ifTagAttribute;

    public FilterHandler(final TagConfig config) {
        super(config);
        sourceTagAttribute = getRequiredAttribute("source");
        targetTagAttribute = getRequiredAttribute("target");
        entryVarTagAttribute = getRequiredAttribute("entryVar");
        testTagAttribute = getAttribute("test");
        filterTagAttribute = getAttribute("filter");
        filterArgumentTagAttribute = getAttribute("filterArgument");
        ifTagAttribute = getAttribute("if");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (ComponentHandler.isNew(parent)) {
                if (! (parent instanceof ActionSource)) {
                    throw new TagException(tag, "Parent component is not an ActionSource");
                }
                ((ActionSource) parent).addActionListener(
                    new FilterActionListener(
                        sourceTagAttribute.getValueExpression(ctx, Object.class),
                        targetTagAttribute.getValueExpression(ctx, Object.class),
                        (String) entryVarTagAttribute.getValueExpression(ctx, String.class).getValue(ctx),
                        testTagAttribute == null ? null : testTagAttribute.getValueExpression(ctx, Boolean.class),
                        filterTagAttribute == null ? null : filterTagAttribute.getValueExpression(ctx, Filter.class),
                        filterArgumentTagAttribute == null ? null : filterArgumentTagAttribute.getValueExpression(ctx, Object.class),
                        ifTagAttribute == null ? null : ifTagAttribute.getValueExpression(ctx, Boolean.class))
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
