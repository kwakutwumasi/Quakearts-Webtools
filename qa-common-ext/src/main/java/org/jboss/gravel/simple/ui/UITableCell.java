package org.jboss.gravel.simple.ui;

import org.jboss.gravel.common.ui.HasHtmlCellAttributes;
import org.jboss.gravel.common.annotation.TldTags;
import org.jboss.gravel.common.annotation.TldTag;

import javax.faces.context.FacesContext;

import java.io.Serializable;

/**
 *
 */
@TldTags ({
    @TldTag (name = "td", description = "Render an HTML td element."),
    @TldTag (name = "th", description = "Render an HTML th element.")
})
public class UITableCell extends UITableRow implements HasHtmlCellAttributes {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5222592513108794267L;
	public static final String COMPONENT_TYPE = "gravel.TableCell";
    public static final String RENDERER_TYPE = "gravel.TableCell";

    public UITableCell() {
        setRendererType(RENDERER_TYPE);
    }

    private String abbr;
    private String axis;
    private String headers;
    private String scope;
    private int rowspan;
    private boolean rowspanSet;
    private int colspan;
    private boolean colspanSet;

    public String getAbbr() {
        return getAttributeValue("abbr", abbr);
    }

    public void setAbbr(final String abbr) {
        this.abbr = abbr;
    }

    public String getAxis() {
        return getAttributeValue("axis", axis);
    }

    public void setAxis(final String axis) {
        this.axis = axis;
    }

    public String getHeaders() {
        return getAttributeValue("headers", headers);
    }

    public void setHeaders(final String headers) {
        this.headers = headers;
    }

    public String getScope() {
        return getAttributeValue("scope", scope);
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }

    public int getRowspan() {
        return getAttributeValue("rowspan", rowspan, rowspanSet, 1);
    }

    public void setRowspan(final int rowspan) {
        this.rowspan = rowspan;
        rowspanSet = true;
    }

    public int getColspan() {
        return getAttributeValue("colspan", colspan, colspanSet, 1);
    }

    public void setColspan(final int colspan) {
        this.colspan = colspan;
        colspanSet = true;
    }

    // ================ State management ================

    private State state;

    public Object saveState(final FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.abbr = abbr;
        state.axis = axis;
        state.headers = headers;
        state.scope = scope;
        state.rowspan = rowspan;
        state.rowspanSet = rowspanSet;
        state.colspan = colspan;
        state.colspanSet = colspanSet;
        return state;
    }

    public void restoreState(final FacesContext context, final Object object) {
        state = (State) object;
        abbr = state.abbr;
        axis = state.axis;
        headers = state.headers;
        scope = state.scope;
        rowspan = state.rowspan;
        rowspanSet = state.rowspanSet;
        colspan = state.colspan;
        colspanSet = state.colspanSet;
        super.restoreState(context, state.superState);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private String abbr;
        private String axis;
        private String headers;
        private String scope;
        private int rowspan;
        private boolean rowspanSet;
        private int colspan;
        private boolean colspanSet;
    }
}
