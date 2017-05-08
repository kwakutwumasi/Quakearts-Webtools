/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.security.auth.webserviceclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "LoginBean", targetNamespace = "http://security.jboss.quakearts.com/")
public interface LoginBean {


    /**
     * 
     * @param domain
     * @param access
     * @param user
     * @return
     *     returns com.quakearts.webapp.security.webserviceclient.Subject
     */
    @WebMethod
    @WebResult(name = "subject", targetNamespace = "http://quakearts.com/xml/jbossPrincipal")
    @RequestWrapper(localName = "authenticate", targetNamespace = "http://security.jboss.quakearts.com/", className = "com.quakearts.webapp.security.webserviceclient.Authenticate")
    @ResponseWrapper(localName = "authenticateResponse", targetNamespace = "http://security.jboss.quakearts.com/", className = "com.quakearts.webapp.security.webserviceclient.AuthenticateResponse")
    public Subject authenticate(
        @WebParam(name = "domain", targetNamespace = "")
        String domain,
        @WebParam(name = "user", targetNamespace = "")
        String user,
        @WebParam(name = "access", targetNamespace = "")
        String access);

}
