package com.quakearts.webapp.security.rest.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ResourceAuthenticationFilter extends AuthenticationFilter {
	private String[] resourcePatterns;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		String resourcePatternsString = filterConfig.getInitParameter("resourcePatterns");
		if(resourcePatternsString == null)
			throw new ServletException("Filter parameter 'resourcePatterns' required");	
		
		resourcePatterns = resourcePatternsString.split(",");
	}
	
	@Override
	protected boolean proceedWithAuthentication(HttpServletRequest request) {
		for(String resourcePattern : resourcePatterns){
			if(request.getPathInfo()!=null 
					&& request.getPathInfo().matches(resourcePattern))
				return true;
		}
		
		return false;
	}
}
