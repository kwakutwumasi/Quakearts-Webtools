package org.jboss.gravel.navigation.executor;

import java.io.IOException;
import javax.el.ValueExpression;
import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public abstract class AbstractRedirectExecutor implements NavigationExecutor {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5133584190760460304L;
	private final ValueExpression targetExpression;
    private final ValueExpression storeMessagesExpression;

    protected AbstractRedirectExecutor(final ValueExpression targetExpression, final ValueExpression storeMessagesExpression) {
        this.targetExpression = targetExpression;
        this.storeMessagesExpression = storeMessagesExpression;
    }

    abstract protected String getTarget(FacesContext context, final String targetAttrValue);

    public final void execute(FacesContext context) {
        final boolean storeMessages;
        final ELContext elContext = context.getELContext();
        final ExternalContext externalContext = context.getExternalContext();
        if (storeMessagesExpression == null) {
            storeMessages = false;
        } else {
            final Object storeMessagesValue = storeMessagesExpression.getValue(elContext);
            if (storeMessagesValue == null) {
                storeMessages = false;
            } else if (storeMessagesValue instanceof Boolean) {
                storeMessages = ((Boolean)storeMessagesValue).booleanValue();
            } else {
                storeMessages = Boolean.valueOf(storeMessagesValue.toString());
            }
        }
        final String target = getTarget(context, targetExpression == null ? null : (String) targetExpression.getValue(elContext));
        if (storeMessages) {
            externalContext.getRequestMap().put("gravel.MessageMapKey", target);
        }
        try {
            // this method also marks the response as complete
            externalContext.redirect(target);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Redirect failed to URL " + target, e);
            FacesException ex = new FacesException(e.getMessage());
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        }
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.navigation.executor.RedirectNavigationExecutor");
}
