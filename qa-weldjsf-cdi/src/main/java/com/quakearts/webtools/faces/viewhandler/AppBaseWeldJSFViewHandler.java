package com.quakearts.webtools.faces.viewhandler;

import java.util.List;
import java.util.Map;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.weld.Container;
import org.jboss.weld.bootstrap.api.helpers.RegistrySingletonProvider;
import org.jboss.weld.context.ConversationContext;
import org.jboss.weld.context.http.HttpConversationContext;

/**A Conversation-Aware, Query-Preserving ViewHandler
 * Thanks to the JBoss WELD and JBoss JBPM Teams for the inspiration and code snippets
 * 
 * @author Kwaku Twumasi kwaku.twumasi@quakearts.com
 *
 */
public class AppBaseWeldJSFViewHandler extends ViewHandlerWrapper {
	private static final String QUERY_STRING_DELIMITER = "?";
	private static final String PARAMETER_PAIR_DELIMITER = "&";
	private static final String PARAMETER_ASSIGNMENT_OPERATOR = "=";

	private static enum Source {
		ACTION, BOOKMARKABLE, REDIRECT, RESOURCE
	}

	private final ViewHandler delegate;
	private volatile ConversationContext conversationContext;
	private static final ThreadLocal<Source> source = new ThreadLocal<Source>();
	private String contextId;

	public AppBaseWeldJSFViewHandler(ViewHandler delegate) {
		this.delegate = delegate;
	}

	/**
	 * Get conversation context.
	 *
	 * @return the conversation context
	 */
	private ConversationContext getConversationContext(String id, FacesContext context) {
		if (conversationContext == null) {
			synchronized (this) {
				if (conversationContext == null) {
					Container container = Container.instance(id);
					conversationContext = container.deploymentManager().instance().select(HttpConversationContext.class)
							.get();

					String parameterName = context.getExternalContext()
							.getInitParameter("com.quakearts.conversation.parameterName");
					if (parameterName != null)
						conversationContext.setParameterName(parameterName);
				}
			}
		}
		return conversationContext;
	}

	@Override
	public String getActionURL(FacesContext facesContext, String viewId) {
		if (contextId == null) {
			if (facesContext.getAttributes().containsKey(Container.CONTEXT_ID_KEY)) {
				contextId = (String) facesContext.getAttributes().get(Container.CONTEXT_ID_KEY);
			} else {
				contextId = RegistrySingletonProvider.STATIC_INSTANCE;
			}
		}

		String actionUrl = super.getActionURL(facesContext, viewId);

		// Preserve request parameters if present
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		if (request.getQueryString() != null) {
			actionUrl = appendExistingQueryString(actionUrl, request.getQueryString());
		}

		final ConversationContext ctx = getConversationContext(contextId, facesContext);
		if (ctx.isActive() && !getSource().equals(Source.BOOKMARKABLE) && !ctx.getCurrentConversation().isTransient()) {
			String parameterName = getConversationContext(contextId, facesContext).getParameterName();
			String parameterValue = ctx.getCurrentConversation().getId();
			if (parameterValue != null) {
				return appendConversationParameter(actionUrl, parameterName, parameterValue);
			} else {
				return actionUrl;
			}
		} else {
			return actionUrl;
		}
	}

	public String appendExistingQueryString(String actionUrl, String queryString) {
		int queryStringIndex = actionUrl.indexOf(QUERY_STRING_DELIMITER);
		String delimiter = queryStringIndex != actionUrl.length()-1?
				(queryStringIndex < 0 ? QUERY_STRING_DELIMITER : PARAMETER_PAIR_DELIMITER)
				:"";
		return new StringBuilder(actionUrl)
				.append(delimiter)
				.append(queryString).toString();
	}

	public String appendConversationParameter(String actionUrl, String parameterName,
			String parameterValue) {
		int queryStringIndex = actionUrl.indexOf(QUERY_STRING_DELIMITER);
		if(queryStringIndex < 0
					|| actionUrl.indexOf(parameterName + PARAMETER_ASSIGNMENT_OPERATOR, queryStringIndex) < 0) {
			String delimiter = queryStringIndex != actionUrl.length()-1?
					(queryStringIndex < 0 ? QUERY_STRING_DELIMITER : PARAMETER_PAIR_DELIMITER)
					:"";
			return new StringBuilder(actionUrl)
					.append(delimiter)
					.append(parameterName)
					.append(PARAMETER_ASSIGNMENT_OPERATOR).append(parameterValue).toString();
		}
		return actionUrl;
	}

	private Source getSource() {
		if (source.get() == null) {
			return Source.ACTION;
		} else {
			return source.get();
		}
	}

	@Override
	public String getBookmarkableURL(FacesContext context, String viewId, Map<String, List<String>> parameters,
			boolean includeViewParams) {
		try {
			source.set(Source.BOOKMARKABLE);
			return super.getBookmarkableURL(context, viewId, parameters, includeViewParams);
		} finally {
			source.remove();
		}
	}

	@Override
	public String getRedirectURL(FacesContext context, String viewId, Map<String, List<String>> parameters,
			boolean includeViewParams) {
		try {
			source.set(Source.REDIRECT);
			return super.getRedirectURL(context, viewId, parameters, includeViewParams);
		} finally {
			source.remove();
		}
	}

	@Override
	public String getResourceURL(FacesContext context, String path) {
		try {
			source.set(Source.RESOURCE);
			return super.getResourceURL(context, path);
		} finally {
			source.remove();
		}
	}

	@Override
	public ViewHandler getWrapped() {
		return delegate;
	}

}
