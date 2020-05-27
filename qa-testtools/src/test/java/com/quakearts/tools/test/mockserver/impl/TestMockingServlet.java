package com.quakearts.tools.test.mockserver.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quakearts.tools.test.mockserver.context.ProcessingContext;
import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;
import com.quakearts.tools.test.mockserver.fi.DefaultAction;
import com.quakearts.tools.test.mockserver.model.MockAction;

public class TestMockingServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7813030742533869339L;
	private List<DefaultAction> defaultActions = new ArrayList<>();
	private List<MockAction> mockActions = new ArrayList<>();

	public TestMockingServlet addDefaultActions(DefaultAction... defaultActions) {
		for(DefaultAction defaultAction:defaultActions) {
			this.defaultActions.add(defaultAction);
		}
		
		return this;
	}
	
	public TestMockingServlet add(MockAction mockAction) {
		this.mockActions.add(mockAction);
		return this;
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProcessingContext context = getProcessingContext(req, resp);
		try {
			processDefaultActions(context);
		} catch (MockServerProcessingException e) {
			throw new ServletException(e);
		}
		
		if(context.responseSent())
			return;

		try {
			mock(context);
		} catch (MockServerProcessingException e) {
			resp.sendError(500, e.getMessage()+(e.getCause()!=null?"; Caused by "+e.getCause().getMessage():""));
		}

	}
	
	private void mock(ProcessingContext context) throws MockServerProcessingException {
		for(MockAction action:mockActions) {
			if(action.requestMatches(context.getHttpRequest())) {
				context.sendResponse(action.executeAction(context.getHttpRequest()));
				return;
			}
		}
		context.sendHttpError(500, "No matching httpRequest found");
	}
	
	private ProcessingContext getProcessingContext(HttpServletRequest req, HttpServletResponse resp) {
		return MockServletProcessingContextBuilder.createProcessingContext(req, resp, "");
	}
	
	private void processDefaultActions(ProcessingContext context) throws MockServerProcessingException {
		if(defaultActions!=null && !defaultActions.isEmpty())
			for (DefaultAction action : defaultActions) {
				action.performAction(context);
				if(context.responseSent())
					break;
			}
	}
}
