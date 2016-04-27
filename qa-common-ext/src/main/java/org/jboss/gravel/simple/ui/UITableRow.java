package org.jboss.gravel.simple.ui;

import org.jboss.gravel.common.ui.HasHtmlCellAlignAttributes;
import org.jboss.gravel.common.annotation.TldTags;
import org.jboss.gravel.common.annotation.TldTag;

import javax.faces.context.FacesContext;

import java.io.Serializable;

/**
 *
 */
@TldTags ({
    @TldTag (name = "tr", description = "Render an HTML tr element."),
    @TldTag (name = "thead", description = "Render an HTML thead element."),
    @TldTag (name = "tbody", description = "Render an HTML tbody element."),
    @TldTag (name = "tfoot", description = "Render an HTML tfoot element.")
})
public class UITableRow extends UISimple implements HasHtmlCellAlignAttributes {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7670927887222662837L;
	public static final String COMPONENT_TYPE = "gravel.TableRow";
    public static final String RENDERER_TYPE = "gravel.TableRow";

    public UITableRow() {
        setRendererType(RENDERER_TYPE);
    }

    private String align;
    private String alignChar;
    private String charoff;
    private String valign;

    public String getAlign() {
        return getAttributeValue("align", align);
    }

    public void setAlign(final String align) {
        this.align = align;
    }

    public String getAlignChar() {
        return getAttributeValue("alignChar", alignChar);
    }

    public void setAlignChar(final String alignChar) {
        this.alignChar = alignChar;
    }

    public String getCharoff() {
        return getAttributeValue("charoff", charoff);
    }

    public void setCharoff(final String charoff) {
        this.charoff = charoff;
    }

    public String getValign() {
        return getAttributeValue("valign", valign);
    }

    public void setValign(final String valign) {
        this.valign = valign;
    }

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.align = align;
        state.alignChar = alignChar;
        state.charoff = charoff;
        state.valign = valign;
        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        align = state.align;
        alignChar = state.alignChar;
        charoff = state.charoff;
        valign = state.valign;
        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private String align;
        private String alignChar;
        private String charoff;
        private String valign;
    }
}
