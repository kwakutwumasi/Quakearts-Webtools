package org.jboss.gravel.simple.ui;

import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.ui.HasHtmlAccessAttributes;
import org.jboss.gravel.common.ui.HasHtmlFocusEventsAttributes;

import javax.faces.context.FacesContext;

import java.io.Serializable;

/**
 *
 */
@TldTag(name = "label", description = "Render an HTML label element.")
public class UILabel extends UISimple implements HasHtmlFocusEventsAttributes, HasHtmlAccessAttributes {

    public static final String COMPONENT_TYPE = "gravel.Label";
    public static final String RENDERER_TYPE = "gravel.Label";
    public static final String COMPONENT_FAMILY = "gravel.simple";

    private static final long serialVersionUID = 1L;

    public UILabel() {
        setRendererType(RENDERER_TYPE);
    }

    // ================ Properties ================

    // Focus events

    private String onfocus;
    private String onblur;

    public String getOnfocus() {
        return getAttributeValue("onfocus", onfocus);
    }

    public void setOnfocus(final String onfocus) {
        this.onfocus = onfocus;
    }

    public String getOnblur() {
        return getAttributeValue("onblur", onblur);
    }

    public void setOnblur(final String onblur) {
        this.onblur = onblur;
    }

    // Access key

    private String accesskey;

    public String getAccesskey() {
        return getAttributeValue("accesskey", accesskey);
    }

    public void setAccesskey(final String accesskey) {
        this.accesskey = accesskey;
    }

    // Label-specific

    private String forId;

    @TldAttribute (
        description = "This attribute explicitly associates the label being defined with another control. " +
            "When present, the value of this attribute resolve to the component id of some other " +
            "control in the same document."
    )
    public String getForId() {
        return getAttributeValue("forId", forId);
    }

    public void setForId(final String forId) {
        this.forId = forId;
    }

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.onfocus = onfocus;
        state.onblur = onblur;
        state.accesskey = accesskey;
        state.forId = forId;
        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        onfocus = state.onfocus;
        onblur = state.onblur;
        accesskey = state.accesskey;
        forId = state.forId;
        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private String onfocus;
        private String onblur;
        private String accesskey;
        private String forId;
    }
}
