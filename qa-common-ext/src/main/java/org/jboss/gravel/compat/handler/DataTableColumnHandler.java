package org.jboss.gravel.compat.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.CompositeFaceletHandler;
import javax.faces.view.facelets.FacetHandler;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletHandler;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.sun.faces.facelets.tag.jsf.ComponentSupport;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public final class DataTableColumnHandler extends ComponentHandler {
    private final ComponentConfig config;
    private final TagAttribute id;
    private String ourId;

    public DataTableColumnHandler(final ComponentConfig config) {
        super(config);
        this.config = config;
        id = getAttribute("id");
    }

    public final void applyForFacets(FaceletContext ctx, final UIComponent parent) throws IOException, FacesException, ELException {
        final boolean debug = log.isLoggable(Level.FINE);
        // first make a list of facet handlers
        List<FaceletHandler> facetHandlers = new ArrayList<FaceletHandler>();

        if (nextHandler instanceof CompositeFaceletHandler) {
            CompositeFaceletHandler composite = (CompositeFaceletHandler) nextHandler;
            for (FaceletHandler handler : composite.getHandlers()) {
                if (handler instanceof FacetHandler) {
                    facetHandlers.add(handler);
                }
            }
        } else {
            if (nextHandler instanceof FacetHandler) {
                facetHandlers.add(nextHandler);
            }
        }

        final FacesContext facesContext = ctx.getFacesContext();
        final Application application = facesContext.getApplication();

        // now locate the component, if it exists; otherwise make a new one
        final String id = ctx.generateUniqueId(tagId);
        UIComponent child = ComponentSupport.findChildByTagId(ctx.getFacesContext(),parent, id);
        if (child != null) {
            if (debug) log.fine(tag + " Column component [" + id + "] found, marking children for cleanup");
            ComponentSupport.markForDeletion(child);
            for (FaceletHandler childHandler : facetHandlers) {
                childHandler.apply(ctx, child);
            }
            ComponentSupport.finalizeForDeletion(child);
            // remove so that it gets re-added at the end of the list
            parent.getChildren().remove(child);
        } else {
            child = application.createComponent(config.getComponentType());
            setAttributes(ctx, child);
            child.getAttributes().put(ComponentSupport.MARK_CREATED, id);
            // assign our unique id
            if (this.id != null) {
                final String value = this.id.getValue(ctx);
                child.setId(value);
                ourId = value;
            } else {
                UIViewRoot root = ComponentSupport.getViewRoot(ctx, parent);
                if (root != null) {
                    String uid = root.createUniqueId();
                    child.setId(uid);
                    ourId = uid;
                }
            }
            if (debug) log.fine(tag + " Column component [" + id + "] created: " + sysToString(child));
            for (FaceletHandler childHandler : facetHandlers) {
                childHandler.apply(ctx, child);
            }
        }
        parent.getChildren().add(child);

    }

    public void onComponentPopulated(FaceletContext ctx, UIComponent c, UIComponent parent) {
        if (ourId != null) {
            // reset ID to the first ID to emulate dataTable behavior
            c.setId(ourId);
        }
    }

    private String sysToString(final Object obj) {
        return obj == null ? "(null)" : obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.compat.handler.DataTableColumnHandler");
}
