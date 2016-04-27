package org.jboss.gravel.navigation.executor;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

/**
 *
 */
public final class UrlRedirectExecutorImpl extends AbstractRedirectExecutor {
    private static final long serialVersionUID = 1L;

    public UrlRedirectExecutorImpl(final ValueExpression targetExpression, final ValueExpression storeMessagesExpression) {
        super(targetExpression, storeMessagesExpression);
    }

    protected String getTarget(FacesContext context, final String targetAttrValue) {
        if (targetAttrValue.charAt(0) == '/') {
            return context.getExternalContext().getRequestContextPath() + targetAttrValue;
        } else {
            return targetAttrValue;
        }
    }
}
