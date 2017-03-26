package org.jboss.gravel.data.ui;

import java.io.Serializable;
import org.jboss.gravel.common.util.NodeMap;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

/**
 *
 */
public final class UIProperties extends UIComponentBase {
    public static final String COMPONENT_TYPE = "gravel.Properties";
    public static final String RENDERER_TYPE = null;
    public static final String COMPONENT_FAMILY = "gravel.data";

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    private NodeMap propertiesMap;
    private String var;

    public NodeMap getPropertiesMap() {
        return propertiesMap;
    }

    public void setPropertiesMap(final NodeMap propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    public String getVar() {
        return var;
    }

    public void setVar(final String var) {
        this.var = var;
    }

    private State state;

    public Object saveState(FacesContext context) {
        if (state == null) {
            state = new State();
        }
        state.superState = super.saveState(context);
        state.propertiesMap = propertiesMap;
        state.var = var;
        return state;
    }

    public void restoreState(FacesContext context, Object stateObject) {
        final State state = (State) stateObject;
        this.state = state;
        propertiesMap = state.propertiesMap;
        var = state.var;
        super.restoreState(context, state.superState);
    }

    public void processRestoreState(FacesContext context, Object state) {
        super.processRestoreState(context, state);
        updatePropertyMap(context);
    }

    public void updatePropertyMap(final FacesContext context) {
        context.getExternalContext().getRequestMap().put(var, propertiesMap);
    }

    public static final class State implements Serializable {
        private static final long serialVersionUID = 1L;

        private Object superState;

        private NodeMap propertiesMap;
        private String var;
    }
}
