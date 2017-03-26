package org.jboss.gravel.simple.ui;

import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.annotation.TldAttribute;

import javax.faces.context.FacesContext;

import java.io.Serializable;

/**
 *
 */
@TldTag (
    name = "form",
    description = "Render an HTML form element."
)
public class UIForm extends UISimple {
    public static final String COMPONENT_TYPE = "gravel.Form";
    public static final String RENDERER_TYPE = "gravel.Form";
    public static final String COMPONENT_FAMILY = "gravel.simple";

    private static final long serialVersionUID = 1L;

    public String getFamily() {
        return super.getFamily();
    }

    private String action;
    private String method;
    private String enctype;
    private String accept;
    private String onsubmit;
    private String onreset;
    private String acceptCharset;

    @TldAttribute (
        description = "This attribute specifies a form processing agent. User agent behavior for a value other than " +
            "an HTTP URI is undefined.  If not given, defaults to the current view's action URL as given by the " +
            "current view handler."
    )
    public String getAction() {
        return getAttributeValue("action", action);
    }

    public void setAction(final String action) {
        this.action = action;
    }

    @TldAttribute(
        description = "This attribute specifies which HTTP method will be used to submit the form data set. Possible " +
            "(case-insensitive) values are \"get\" (the default) and \"post\"."
    )
    public String getMethod() {
        return getAttributeValue("method", method);
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    @TldAttribute(
        description = "This attribute specifies the content type used to submit the form to the server (when the value " +
            "of the \"method\" attribute is \"post\")."
    )
    public String getEnctype() {
        return getAttributeValue("enctype", enctype);
    }

    public void setEnctype(final String enctype) {
        this.enctype = enctype;
    }

    @TldAttribute (
        description = "This attribute specifies a comma-separated list of content types that a server processing " +
            "this form will handle correctly."
    )
    public String getAccept() {
        return getAttributeValue("accept", accept);
    }

    public void setAccept(final String accept) {
        this.accept = accept;
    }

    @TldAttribute (
        description = "Render the HTML onsubmit attribute.  The onsubmit event occurs when a form is submitted."
    )
    public String getOnsubmit() {
        return getAttributeValue("onsubmit", onsubmit);
    }

    public void setOnsubmit(final String onsubmit) {
        this.onsubmit = onsubmit;
    }

    @TldAttribute (
        description = "Render the HTML onreset attribute.  The onreset event occurs when a form is reset."
    )
    public String getOnreset() {
        return getAttributeValue("onreset", onreset);
    }

    public void setOnreset(final String onreset) {
        this.onreset = onreset;
    }

    @TldAttribute (
        description = "This attribute specifies the list of character encodings for input data that is accepted by " +
            "the server processing this form. The value is a space- and/or comma-delimited list of charset values."
    )
    public String getAcceptCharset() {
        return getAttributeValue("acceptCharset", acceptCharset);
    }

    public void setAcceptCharset(final String acceptCharset) {
        this.acceptCharset = acceptCharset;
    }

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.action = action;
        state.method = method;
        state.enctype = enctype;
        state.accept = accept;
        state.onsubmit = onsubmit;
        state.onreset = onreset;
        state.acceptCharset = acceptCharset;
        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        action = state.action;
        method = state.method;
        enctype = state.enctype;
        accept = state.accept;
        onsubmit = state.onsubmit;
        onreset = state.onreset;
        acceptCharset = state.acceptCharset;
        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private String action;
        private String method;
        private String enctype;
        private String accept;
        private String onsubmit;
        private String onreset;
        private String acceptCharset;
    }
}
