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
package com.quakearts.webapp.facelets.util;

public class WebPageBean {
	private int statusCode;
	private String responseMessage, content;

	public WebPageBean(String content, String responseMessage, int statusCode){
		this.content = content;
		this.responseMessage = responseMessage;
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getContent() {
		return content;
	}
	
}
