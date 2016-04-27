package org.jboss.gravel.simple.renderer;

import java.io.IOException;
import org.jboss.gravel.common.renderer.Element;
import org.jboss.gravel.simple.ui.UILabel;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 */
public class LabelRenderer<T extends UILabel> extends SimpleRenderer<T> {
    protected LabelRenderer(final String element) {
        super(element);
    }

    protected void writeAttributes(final Element<T> elem, final T component) throws IOException {
        super.writeAttributes(elem, component);
        writeHtmlAccess(elem, component);
        writeHtmlFocus(elem, component);
        final String forId = component.getForId();
        if (forId != null) {
            final UIComponent forComponent = component.findComponent(forId);
            if (forComponent != null) {
                final String clientId = forComponent.getClientId(FacesContext.getCurrentInstance());
                if (clientId != null) {
                    elem.writeAttribute("for", clientId);
                } else {
                    throw new FacesException("Component is missing client ID");
                }
            } else {
                throw new FacesException("Component '" + forId + "' not found");
            }
        }
    }

    public static LabelRenderer<UILabel> labelRenderer(final String element) {
        return new LabelRenderer<UILabel>(element);
    }
}
