package org.jboss.gravel.navigation.executor;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public final class StayRedirectExecutorImpl extends AbstractRedirectExecutor {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7406276819537368281L;

	public StayRedirectExecutorImpl(final ValueExpression storeMessagesExpression) {
        super(null, storeMessagesExpression);
    }

    protected String getTarget(FacesContext context, final String targetAttrValue) {
        final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        final StringBuilder builder = new StringBuilder(request.getRequestURI());
        if (request.getQueryString() != null) {
            builder.append('?').append(request.getQueryString());
        }
        return builder.toString();
    }
}
