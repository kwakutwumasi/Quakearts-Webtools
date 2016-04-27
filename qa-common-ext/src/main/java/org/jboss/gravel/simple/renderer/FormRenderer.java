package org.jboss.gravel.simple.renderer;

import org.jboss.gravel.simple.ui.UIForm;
import org.jboss.gravel.common.renderer.Element;
import java.io.IOException;

import javax.faces.context.FacesContext;

/**
 *
 */
public final class FormRenderer<T extends UIForm> extends SimpleRenderer<T> {
    protected FormRenderer(final String element) {
        super(element);
    }

    protected void writeAttributes(final Element<T> elem, final T component) throws IOException {
        super.writeAttributes(elem, component);
        elem.writeId("name", false);
        final String action = component.getAction();
        if (action == null) {
            final FacesContext context = FacesContext.getCurrentInstance();
            elem.writeAttribute("action", context.getApplication().getViewHandler().getActionURL(context, context.getViewRoot().getViewId()));
        } else {
            elem.writeAttribute("action", action);
        }
        elem.writeAttribute("method", component.getMethod());
        elem.writeAttribute("enctype", component.getEnctype());
        elem.writeAttribute("accept", component.getAccept());
        elem.writeAttribute("onsubmit", component.getOnsubmit());
        elem.writeAttribute("onreset", component.getOnreset());
        elem.writeAttribute("accept-charset", component.getAcceptCharset());
    }

    public static FormRenderer<UIForm> formRenderer(final String element) {
        return new FormRenderer<UIForm>(element);
    }
}
