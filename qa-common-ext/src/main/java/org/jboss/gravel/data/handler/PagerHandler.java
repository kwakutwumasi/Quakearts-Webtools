package org.jboss.gravel.data.handler;

import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.data.action.PagerActionListener;

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

import java.io.IOException;

/**
 *
 */
@TldTag (
    name = "pager",
    description = "Calculate paging information for use by another component.",
    attributes = {
        @TldAttribute (
            name = "value",
            description = "An EL expression that contains the collection or map to be evaluated.",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "target",
            description = "An EL expression into which the paging information will be stored. The paging information " +
                "is stored in a bean with the following properties: <ul>" +
                "<li><code>page</code> - the current actual page number (if the requested page is past the end, it will " +
                "be clamped to this value</li>" +
                "<li><code>totalPages</code> - the total number of pages available (0 for an empty data set)</li>" +
                "<li><code>pageSize</code> - the number of results per page</li>" +
                "<li><code>thisPageSize</code> - the number of results on the current page</li>" +
                "<li><code>firstPage</code> - a boolean that is true if this is the first page</li>" +
                "<li><code>lastPage</code> - a boolean that is true if this is the last page</li>" +
                "<li><code>first</code> - the index of the first item in the collection to display</li>" +
                "</ul>",
            required = true,
            deferredType = Object.class
        ),
        @TldAttribute (
            name = "pageSize",
            description = "The number of results that will be displayed per page.  If not given, defaults to 10.",
            deferredType = int.class
        ),
        @TldAttribute (
            name = "page",
            description = "The current page number.  If not given, defaults to 1.",
            deferredType = int.class
        )
    }
)
public final class PagerHandler extends TagHandler {
    private final TagAttribute valueTagAttribute;
    private final TagAttribute targetTagAttribute;
    private final TagAttribute pageSizeTagAttribute;
    private final TagAttribute pageTagAttribute;

    public PagerHandler(final TagConfig config) {
        super(config);
        valueTagAttribute = getRequiredAttribute("value");
        targetTagAttribute = getRequiredAttribute("target");
        pageSizeTagAttribute = getAttribute("pageSize");
        pageTagAttribute = getAttribute("page");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (ComponentHandler.isNew(parent)) {
                if (! (parent instanceof ActionSource)) {
                    throw new TagException(tag, "Parent component is not an ActionSource");
                }
                ((ActionSource) parent).addActionListener(
                    new PagerActionListener(
                        valueTagAttribute.getValueExpression(ctx, Object.class),
                        pageTagAttribute == null ? null : pageTagAttribute.getValueExpression(ctx, int.class),
                        pageSizeTagAttribute == null ? null : pageSizeTagAttribute.getValueExpression(ctx, int.class),
                        targetTagAttribute.getValueExpression(ctx, Object.class)
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
