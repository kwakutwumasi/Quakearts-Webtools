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
package org.jboss.gravel.compat.handler;

import java.io.IOException;
import org.jboss.gravel.common.handler.CollectionHandler;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.CompositeFaceletHandler;
import javax.faces.view.facelets.FaceletHandler;
import javax.faces.view.facelets.MetaRuleset;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.ComponentConfig;

import javax.faces.component.UIComponent;

/**
 *
 */
public final class DataTableHandler extends CollectionHandler {
    public DataTableHandler(final ComponentConfig componentConfig) {
        super(componentConfig);
    }

    public void onComponentPopulated(FaceletContext ctx, UIComponent c, UIComponent parent) {
        // First, run through the columns and create facet-only versions of each column on the top level
        // This is so that column facets will work

        try {
            if (nextHandler instanceof CompositeFaceletHandler) {
                CompositeFaceletHandler composite = (CompositeFaceletHandler) nextHandler;
                for (FaceletHandler handler : composite.getHandlers()) {
                    if (handler instanceof DataTableColumnHandler) {
                        ((DataTableColumnHandler) handler).applyForFacets(ctx, c);
                    }
                }
            } else {
                if (nextHandler instanceof DataTableColumnHandler) {
                    ((DataTableColumnHandler) nextHandler).applyForFacets(ctx, c);
                }
            }
        } catch (IOException ex) {
            TagException tex = new TagException(tag, ex.getMessage());
            tex.setStackTrace(ex.getStackTrace());
            throw tex;
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    protected MetaRuleset createMetaRuleset(final Class type) {
        final MetaRuleset metaRuleset = super.createMetaRuleset(type);
        metaRuleset.alias("rows", "limit");
        return metaRuleset;
    }
}
