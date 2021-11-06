/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.rest.client.net.http;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.Proxy;

public class HttpHandler extends URLStreamHandler {
	protected String proxy;
	protected int proxyPort;

	@Override
	protected int getDefaultPort() {
		return 80;
	}

	public HttpHandler() {
		proxy = null;
		proxyPort = -1;
	}

	public HttpHandler(String proxy, int port) {
		this.proxy = proxy;
		this.proxyPort = port;
	}

	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		return openConnection(u, (Proxy) null);
	}

	@Override
	protected URLConnection openConnection(URL u, Proxy p) throws IOException {
		return new HttpURLConnectionImpl(u, p, this);
	}
}
