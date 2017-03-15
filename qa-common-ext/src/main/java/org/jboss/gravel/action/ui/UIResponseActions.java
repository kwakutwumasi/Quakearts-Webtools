package org.jboss.gravel.action.ui;

import org.jboss.gravel.common.annotation.TldTag;

import javax.el.MethodExpression;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionListener;
import javax.faces.render.Renderer;

/**
 *
 */
@TldTag(
    name = "responseActions",
    description = "A component that contains a series of actions to be executed when the " +
        "response is rendered.  Any ActionListeners registered within this component will be " +
        "called during the RENDER_RESPONSE phase regardless of whether the request is a postback."
)
public final class UIResponseActions extends UIComponentBase implements ActionSource2 {
    public static final String COMPONENT_TYPE = "gravel.action.ResponseActions";
    public static final String COMPONENT_FAMILY = "gravel.action";
    public static final String RESPONSEACTION = "gravel.response.actions";
    
    
    public UIResponseActions() {
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
    @Override
    protected Renderer getRenderer(FacesContext context) {
    	return null;
    }
    
    @Override
    public String getRendererType() {
    	return null;
    }
    
    @Override
    public boolean isRendered() {
    	return false;
    }

	@Override
	public void addActionListener(ActionListener listener) {
		addFacesListener(listener);
	}

	@Override
	public MethodBinding getAction() {
		return null;
	}

	@Override
	public MethodBinding getActionListener() {
		return null;
	}

    public ActionListener[] getActionListeners() {
        ActionListener al[] = (ActionListener [])
        getFacesListeners(ActionListener.class);
        return (al);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */ 
    public void removeActionListener(ActionListener listener) {
        removeFacesListener(listener);
    }

	@Override
	public boolean isImmediate() {
		return false;
	}

	@Override
	public void setAction(MethodBinding action) {
	}

	@Override
	public void setActionListener(MethodBinding actionListener) {
	}

	@Override
	public void setImmediate(boolean immediate) {
	}

	@Override
	public MethodExpression getActionExpression() {
		return null;
	}

	@Override
	public void setActionExpression(MethodExpression action) {
	}
}
