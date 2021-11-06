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
package com.quakearts.rest.client.net.https;

import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import java.net.Proxy;
import java.io.IOException;

public class DelegateHttpsURLConnection extends AbstractDelegateHttpsURLConnection {

	protected HttpsURLConnection httpsURLConnection;

	DelegateHttpsURLConnection(URL url, Proxy proxy, HttpsHandler handler, HttpsURLConnection httpsURLConnection)
			throws IOException {
		super(url, proxy, handler);
		this.httpsURLConnection = httpsURLConnection;
	}
	
	@Override
	protected URLConnection getAlternateURLConnection() {
		return httpsURLConnection;
	}

	@Override
	protected SSLSocketFactory getSSLSocketFactory() {
		return httpsURLConnection.getSSLSocketFactory();
	}

	@Override
	protected HostnameVerifier getHostnameVerifier() {
		return httpsURLConnection.getHostnameVerifier();
	}

	@Override
	public int hashCode() {
		return Objects.hash(httpsURLConnection);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DelegateHttpsURLConnection))
			return false;
		DelegateHttpsURLConnection other = (DelegateHttpsURLConnection) obj;
		return Objects.equals(httpsURLConnection, other.httpsURLConnection);
	}
}
