package org.jboss.gravel;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * A view handler that preserves GET query parameters on action URLs.  This
 * allows {@code &lt;h:form&gt;} elements to generate actions that preserve
 * the same parameters used to initially generate the view on postback.
 *
 * To use, add the fully-qualified name of this class to the {@code application} section
 * of your {@code faces-config.xml} as an additional {@code view-handler}, after the Facelets
 * view handler.
 */
public final class QueryPreservingViewHandler extends ViewHandlerWrapper {
    private final ViewHandler viewHandler;

    public QueryPreservingViewHandler(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public String getActionURL(FacesContext facesContext, String string) {
        final String origActionURL = viewHandler.getActionURL(facesContext, string);
        final Object requestObject = facesContext.getExternalContext().getRequest();
        if (requestObject instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) requestObject;
            final String queryString = request.getQueryString();
            if (queryString != null && queryString.length() > 0) {
                final String newActionURL = origActionURL + "?" + queryString;
                return newActionURL;
            }
        }
        return origActionURL;
    }

    public ViewHandler getWrapped() {
        return viewHandler;
    }
}
