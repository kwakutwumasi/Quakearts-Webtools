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
package org.jboss.gravel.simple;

import org.jboss.gravel.simple.renderer.LabelRenderer;
import org.jboss.gravel.simple.renderer.SimpleRenderer;
import org.jboss.gravel.simple.renderer.TableCellRenderer;
import org.jboss.gravel.simple.renderer.TableRowRenderer;
import org.jboss.gravel.simple.renderer.FormRenderer;
import org.jboss.gravel.simple.ui.UILabel;
import org.jboss.gravel.simple.ui.UISimple;
import org.jboss.gravel.simple.ui.UITableCell;
import org.jboss.gravel.simple.ui.UITableRow;
import org.jboss.gravel.simple.ui.UIForm;

import com.sun.faces.facelets.tag.AbstractTagLibrary;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.render.Renderer;

/**
 *
 */
public final class SimpleLibrary extends AbstractTagLibrary {
    public SimpleLibrary() {
        super("http://gravel.jboss.org/jsf/2.0/simple");

        final FacesContext facesContext = FacesContext.getCurrentInstance();
		RenderKitFactory factory = (RenderKitFactory) FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
        final RenderKit renderKit = factory.getRenderKit(facesContext, RenderKitFactory.HTML_BASIC_RENDER_KIT);
        final Application application = facesContext.getApplication();

        application.addComponent(UISimple.COMPONENT_TYPE, UISimple.class.getName());
        application.addComponent(UITableRow.COMPONENT_TYPE, UITableRow.class.getName());
        application.addComponent(UITableCell.COMPONENT_TYPE, UITableCell.class.getName());
        application.addComponent(UILabel.COMPONENT_TYPE, UILabel.class.getName());
        application.addComponent(UIForm.COMPONENT_TYPE, UIForm.class.getName());

        // Add all simple components
        addSimple(renderKit, "div");
        addSimple(renderKit, "span");
        addSimple(renderKit, "dl");
        addSimple(renderKit, "ul");
        addSimple(renderKit, "ol");
        addSimple(renderKit, "li");
        addSimple(renderKit, "dd");
        addSimple(renderKit, "dt");
        addSimple(renderKit, "h1");
        addSimple(renderKit, "h2");
        addSimple(renderKit, "h3");
        addSimple(renderKit, "h4");
        addSimple(renderKit, "h5");
        addSimple(renderKit, "h6");
        addSimple(renderKit, "address");
        addSimple(renderKit, "p");
        addSimple(renderKit, "em");
        addSimple(renderKit, "strong");
        addSimple(renderKit, "dfn");
        addSimple(renderKit, "code");
        addSimple(renderKit, "samp");
        addSimple(renderKit, "kbd");
        addSimple(renderKit, "var");
        addSimple(renderKit, "cite");
        addSimple(renderKit, "abbr");
        addSimple(renderKit, "acronym");
        addSimple(renderKit, "pre");
        addSimple(renderKit, "sub");
        addSimple(renderKit, "sup");
        addSimple(renderKit, "tt");
        addSimple(renderKit, "i");
        addSimple(renderKit, "b");
        addSimple(renderKit, "big");
        addSimple(renderKit, "small");
        addSimple(renderKit, "caption");
        addTableRow(renderKit, "thead");
        addTableRow(renderKit, "tbody");
        addTableRow(renderKit, "tfoot");
        addTableRow(renderKit, "tr");
        addTableCell(renderKit, "td");
        addTableCell(renderKit, "th");
        addLabel(renderKit, "label");
        addForm(renderKit, "form");
    }

    private void add(final RenderKit renderKit, final String rendererType, final String family, final String componentType, final String tag, final Renderer renderer) {
        final String composedRendererType = rendererType + ":" + tag;
        renderKit.addRenderer(family, composedRendererType, renderer);
        addComponent(tag, componentType, composedRendererType);
    }

    private void addSimple(final RenderKit renderKit, final String tag) {
        add(renderKit, UISimple.RENDERER_TYPE, UISimple.COMPONENT_FAMILY, UISimple.COMPONENT_TYPE, tag, SimpleRenderer.simpleRenderer(tag));
    }

    private void addTableCell(final RenderKit renderKit, final String tag) {
        add(renderKit, UITableCell.RENDERER_TYPE, UITableCell.COMPONENT_FAMILY, UITableCell.COMPONENT_TYPE, tag, TableCellRenderer.tableCellRenderer(tag));
    }

    private void addTableRow(final RenderKit renderKit, final String tag) {
        add(renderKit, UITableRow.RENDERER_TYPE, UITableRow.COMPONENT_FAMILY, UITableRow.COMPONENT_TYPE, tag, TableRowRenderer.tableRowRenderer(tag));
    }

    private void addLabel(final RenderKit renderKit, final String tag) {
        add(renderKit, UILabel.RENDERER_TYPE, UILabel.COMPONENT_FAMILY, UILabel.COMPONENT_TYPE, tag, LabelRenderer.labelRenderer(tag));
    }

    private void addForm(final RenderKit renderKit, final String tag) {
        add(renderKit, UIForm.RENDERER_TYPE, UIForm.COMPONENT_FAMILY, UIForm.COMPONENT_TYPE, tag, FormRenderer.formRenderer(tag));
    }
}
