package org.jboss.gravel.navigation;

import org.jboss.gravel.navigation.action.NavigationState;
import org.jboss.gravel.navigation.executor.NavigationExecutor;
import org.jboss.gravel.navigation.executor.StayExecutorImpl;

import javax.el.ELException;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.component.ActionSource;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.EvaluationException;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 */
@SuppressWarnings("deprecation")
public final class GravelLegacyNavigationHandler extends NavigationHandler {
    private static final NavigationExecutor DEFAULT_EXECUTOR = new StayExecutorImpl();
    public static final String NAV_OUTCOME_KEY = "org.jboss.gravel.navigation.NAV_OUTCOME_KEY";
    private NavigationHandler wrapped;
    
    public GravelLegacyNavigationHandler(NavigationHandler wrapped){
        FacesContext.getCurrentInstance().getApplication().setActionListener(new DefaultActionListener());
        this.wrapped = wrapped;
    }
    
    public void handleNavigation(final FacesContext context, final String fromAction, final String actionOutcome) {
        final NavigationExecutor executor;
        final NavigationState state = (NavigationState) context.getExternalContext().getRequestMap().get(NavigationState.NAV_KEY);
        if (state == null) {
            log.fine("No navigation state in current request context.  Staying on current view.");
            executor = DEFAULT_EXECUTOR;
        } else {
        	if (state.hasRule(actionOutcome)) {
                executor = state.getRule(actionOutcome);
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Navigation rule '" + actionOutcome + "' matched to executor " + executor);
                }
            } else if (state.hasRule("default")) {
                executor = state.getRule("default");
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Navigation rule '" + actionOutcome + "' not matched; using registered 'default' executor " + executor);
                }
            } else {
                executor = DEFAULT_EXECUTOR;
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Navigation rule '" + actionOutcome + "' not matched and no registered 'default' executor; defaulting to " + executor);
                }
            }
        }
        executor.execute(context);
    }

    private class DefaultActionListener implements ActionListener, Serializable {

		/** This class changes the default behavior for all ActionSource/UICommand components to
		 * honor the tag based navigation scheme. Navigation outcome is pulled from the request map
		 * using the NAV_OUTCOME_KEY key.
		 */
		private static final long serialVersionUID = 4374498799648342992L;

		@Override
		public void processAction(ActionEvent event)
				throws AbortProcessingException {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        Application application = facesContext.getApplication();

	        UIComponent comp = event.getComponent();
	        if(!(comp instanceof ActionSource2))
	        	throw new AbortProcessingException("GravelNavigation only works with ActionSource2 components");
	        
	        ActionSource actionSource = (ActionSource)event.getComponent();
	        MethodBinding methodBinding = actionSource.getAction();

	        String fromAction;
	        String outcome;
	        if (methodBinding == null)
	        {
	        	Object outcomeObj = facesContext.getExternalContext().getRequestMap().get(NAV_OUTCOME_KEY);
	        	if(outcomeObj!=null)
	        		outcome = outcomeObj.toString();
	        	else
		            outcome = null;

	        	fromAction = null;
	        }
	        else
	        {
	            fromAction = methodBinding.getExpressionString();
	            try
	            {
	                outcome = (String) methodBinding.invoke(facesContext, null);
	            }
	            catch (EvaluationException e)
	            {
	                Throwable cause = e.getCause();
	                if (cause != null && cause instanceof AbortProcessingException)
	                {
	                    throw (AbortProcessingException)cause;
	                }
	                else
	                {
	                    throw new FacesException("Error calling action method of component with id " + event.getComponent().getClientId(facesContext), e);
	                }
	            }
	            catch (RuntimeException e)
	            {
	                throw new FacesException("Error calling action method of component with id " + event.getComponent().getClientId(facesContext), e);
	            }
	        }

	        NavigationHandler navigationHandler = application.getNavigationHandler();
	        navigationHandler.handleNavigation(facesContext,
	                                           fromAction,
	                                           outcome);
			//Render Response if needed
			facesContext.renderResponse();
		}
    }
    
    private static final Logger log = Logger.getLogger("org.jboss.gravel.navigation.GravelNavigationHandler");
}
