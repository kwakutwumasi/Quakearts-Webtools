/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.security.rest.cdi;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import com.quakearts.webapp.security.rest.SecurityContext;
import com.quakearts.webapp.security.rest.exception.SecurityException;

/**CDI interceptor. Authenticates access to the method by checking the {@linkplain SecurityContext}
 * for roles specified in the @{@linkplain RequireAuthorization} annotation.
 * @author kwakutwumasi-afriyie
 *
 */
@Interceptor @RequireAuthorization
@Priority(Interceptor.Priority.APPLICATION)
public class AuthorizationInterceptor {

	@AroundInvoke
	public Object authorizeSubject(InvocationContext context) throws Exception {
		RequireAuthorization authorization = context.getMethod().getAnnotation(RequireAuthorization.class);
		if(authorization==null)
			authorization = context.getMethod().getDeclaringClass().getAnnotation(RequireAuthorization.class);
	
		if(authorization==null)
			throw new SecurityException("No authorization parameter found to check for roles");

		SecurityContext securityContext = SecurityContext.getSecurityContext();
		if(securityContext.getSubject()==null)
			throw new SecurityException("No Subject loaded for verification");
		
		boolean proceed = true;
		if(authorization.allow().length>0){
			proceed = false;
			List<String> allowed = Arrays.asList(authorization.allow());
			
			for(Principal principal:securityContext.getSubject().getPrincipals()){
				if(allowed.contains(principal.getName())){
					proceed = true;
					break;
				}
			}
		}

		if(proceed && authorization.deny().length>0){
			List<String> deny = Arrays.asList(authorization.deny());
			
			for(Principal principal:securityContext.getSubject().getPrincipals()){
				if(deny.contains(principal.getName())){
					proceed = false;
					break;
				}
			}
		}
		
		if(proceed)
			return context.proceed();
		else
			throw new SecurityException("Subject does not have the necessary permissions");
	}
}
