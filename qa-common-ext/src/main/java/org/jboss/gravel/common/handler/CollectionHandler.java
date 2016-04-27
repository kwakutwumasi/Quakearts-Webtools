/*
    Gravel - Component library for JSF
    Copyright (C) 2007  David M. Lloyd

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jboss.gravel.common.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.gravel.common.ui.CollectionComponent;
import org.jboss.gravel.common.ui.UICollectionEntry;

import com.sun.faces.facelets.el.VariableMapperWrapper;
import com.sun.faces.facelets.tag.jsf.ComponentSupport;

import javax.faces.view.facelets.AttributeHandler;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.CompositeFaceletHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletHandler;
import javax.faces.view.facelets.FacetHandler;
import javax.faces.view.facelets.TagException;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.VariableMapper;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class CollectionHandler extends ComponentHandler {

    private final ComponentConfig componentConfig;

    public CollectionHandler(final ComponentConfig componentConfig) {
        super(componentConfig);
        this.componentConfig = componentConfig;
    }

    public void applyNextHandler(final FaceletContext ctx, final UIComponent c) throws IOException, FacesException, ELException {
        try {
            final boolean debug = log.isLoggable(Level.FINE);

            final FaceletHandler nextHandler = componentConfig.getNextHandler();

            final List<FaceletHandler> nonCollectionHandlers = new ArrayList<FaceletHandler>();
            final List<FaceletHandler> children = new ArrayList<FaceletHandler>();

            // Sort subsequent tag handlers into facets and children
            if (nextHandler instanceof CompositeFaceletHandler) {
                CompositeFaceletHandler composite = (CompositeFaceletHandler) nextHandler;
                for (FaceletHandler handler : composite.getHandlers()) {
                    if (isNonCollectionHandler(handler)) {
                        nonCollectionHandlers.add(handler);
                    } else {
                        children.add(handler);
                    }
                }
            } else {
                if (isNonCollectionHandler(nextHandler)) {
                    nonCollectionHandlers.add(nextHandler);
                } else {
                    children.add(nextHandler);
                }
            }

            if (debug) log.fine(tag + " Applying non-collection and facet handlers");

            for (FaceletHandler handler : nonCollectionHandlers) {
                handler.apply(ctx, c);
            }

            final CollectionComponent component = (CollectionComponent) c;
            final List<?> valueList = component.getValueList();

            final int componentFirst = component.getFirst();
            final int componentLimit = component.getLimit();

            final int first = componentFirst == -1 ? 0 : componentFirst;
            final int limit = componentLimit == -1 ? valueList.size() : Math.min(valueList.size(), componentLimit);

            if (first >= limit) {
                if (debug) log.fine(tag + " All collection rows eliminated, not applying any members");
                return;
            }

            if (debug) log.fine(tag + " Applying collection: " + sysToString(valueList) + " (member range: " + first + " to " + limit + ")");

            final String var = component.getVar();
            final String idVar = component.getIdVar();

            final String id = (String) c.getAttributes().get(ComponentSupport.MARK_CREATED);
            final StringBuilder idBuilder = new StringBuilder(60);

            final VariableMapper orig = ctx.getVariableMapper();
            final FacesContext facesContext = ctx.getFacesContext();
            final Application application = facesContext.getApplication();
            final ExpressionFactory expressionFactory = application.getExpressionFactory();

            for (int i = first; i < limit; i++) {
                // first generate tag Id from mine
                idBuilder.setLength(0);
                idBuilder.append(id);
                idBuilder.append(":");
                idBuilder.append(i);
                final String subId = idBuilder.toString();

                final VariableMapperWrapper memberMapper = new VariableMapperWrapper(orig);
                ctx.setVariableMapper(memberMapper);
                if (var != null) {
                    memberMapper.setVariable(var, expressionFactory.createValueExpression(valueList.get(i), Object.class));
                }
                if (idVar != null) {
                    memberMapper.setVariable(idVar, expressionFactory.createValueExpression(new Integer(i), Integer.class));
                }

                // now locate the component, if it exists; otherwise make a new one
                UICollectionEntry child = (UICollectionEntry) ComponentSupport.findChildByTagId(facesContext,c, subId);
                if (child != null) {
                    if (debug) log.fine(tag + " Collection component [" + subId + "] found, marking children for cleanup");
                    child.setPrependId(component.isPrependId());
                    ComponentSupport.markForDeletion(child);
                    for (FaceletHandler childHandler : children) {
                        childHandler.apply(ctx, child);
                    }
                    ComponentSupport.finalizeForDeletion(child);
                    // remove so that it gets re-added at the end of the list
                    c.getChildren().remove(child);
                } else {
                    child = (UICollectionEntry) application.createComponent(UICollectionEntry.COMPONENT_TYPE);
                    child.setPrependId(component.isPrependId());
                    child.setRowId(i);
                    child.getAttributes().put(ComponentSupport.MARK_CREATED, subId);
                    if (debug) log.fine(tag + " Collection component [" + subId + "] created: " + sysToString(child));
                    for (FaceletHandler childHandler : children) {
                        childHandler.apply(ctx, child);
                    }
                }
                c.getChildren().add(child);
            }
            ctx.setVariableMapper(orig);
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    /**
     * Determine whether to apply the tag to the parent collection, or once to each child.
     * Some tags should always be applied to the collection, like facets and attribute handlers.  Some
     * always belong on the children, like component handlers.
     *
     * @param handler
     * @return
     */
    private boolean isNonCollectionHandler(final FaceletHandler handler) {
        // TODO - I am unsure that this is the best approach

        return handler instanceof NonCollectionHandler ||
            handler instanceof FacetHandler ||
            handler instanceof AttributeHandler;
    }

    private String sysToString(final Object obj) {
        return obj == null ? "(null)" : obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
    }

    private final Logger log = Logger.getLogger(getClass().getName());
}
