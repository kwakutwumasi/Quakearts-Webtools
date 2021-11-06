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
import java.net.Proxy;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import java.io.*;
import javax.net.ssl.*;

import com.quakearts.rest.client.net.IPAddressUtil;

import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Map;
import java.util.Objects;
import java.util.List;

public class HttpsURLConnectionImpl extends HttpsURLConnection {
	protected DelegateHttpsURLConnection delegate;

	static URL checkURL(URL url) throws IOException {
		if (url != null && url.toExternalForm().indexOf('\n') > -1) {
			throw new MalformedURLException("Illegal character in URL");
		}
		
		String authority = IPAddressUtil.checkAuthority(url);
		
		if (authority != null) {
			throw new MalformedURLException(authority);
		}
		return url;
	}

	public HttpsURLConnectionImpl(URL u, Proxy p, HttpsHandler handler) throws IOException {
		super(checkURL(u));
		delegate = new DelegateHttpsURLConnection(url, p, handler, this);
	}

	@Override
	public void connect() throws IOException {
		delegate.connect();
	}

	@Override
	public String getCipherSuite() {
		return delegate.getCipherSuite();
	}

	@Override
	public Certificate[] getLocalCertificates() {
		return delegate.getLocalCertificates();
	}

	@Override
	public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
		return delegate.getServerCertificates();
	}

	@Override
	public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
		return delegate.getPeerPrincipal();
	}

	@Override
	public Principal getLocalPrincipal() {
		return delegate.getLocalPrincipal();
	}

	@Override
	public synchronized OutputStream getOutputStream() throws IOException {
		return delegate.getOutputStream();
	}

	@Override
	public synchronized InputStream getInputStream() throws IOException {
		return delegate.getInputStream();
	}

	@Override
	public InputStream getErrorStream() {
		return delegate.getErrorStream();
	}

	@Override
	public void disconnect() {
		delegate.disconnect();
	}

	@Override
	public boolean usingProxy() {
		return delegate.usingProxy();
	}

	@Override
	public Map<String, List<String>> getHeaderFields() {
		return delegate.getHeaderFields();
	}

	@Override
	public String getHeaderField(String name) {
		return delegate.getHeaderField(name);
	}

	@Override
	public String getHeaderField(int n) {
		return delegate.getHeaderField(n);
	}

	@Override
	public String getHeaderFieldKey(int n) {
		return delegate.getHeaderFieldKey(n);
	}

	@Override
	public void setRequestProperty(String key, String value) {
		delegate.setRequestProperty(key, value);
	}

	@Override
	public void addRequestProperty(String key, String value) {
		delegate.addRequestProperty(key, value);
	}

	@Override
	public int getResponseCode() throws IOException {
		return delegate.getResponseCode();
	}

	@Override
	public String getRequestProperty(String key) {
		return delegate.getRequestProperty(key);
	}

	@Override
	public Map<String, List<String>> getRequestProperties() {
		return delegate.getRequestProperties();
	}

	@Override
	public void setInstanceFollowRedirects(boolean shouldFollow) {
		delegate.setInstanceFollowRedirects(shouldFollow);
	}

	@Override
	public boolean getInstanceFollowRedirects() {
		return delegate.getInstanceFollowRedirects();
	}

	@Override
	public void setRequestMethod(String method) throws ProtocolException {
		delegate.setRequestMethod(method);
	}

	@Override
	public String getRequestMethod() {
		return delegate.getRequestMethod();
	}

	@Override
	public String getResponseMessage() throws IOException {
		return delegate.getResponseMessage();
	}

	@Override
	public long getHeaderFieldDate(String name, long defaultDate) {
		return delegate.getHeaderFieldDate(name, defaultDate);
	}

	@Override
	public Permission getPermission() throws IOException {
		return delegate.getPermission();
	}

	@Override
	public URL getURL() {
		return delegate.getURL();
	}

	@Override
	public int getContentLength() {
		return delegate.getContentLength();
	}

	@Override
	public long getContentLengthLong() {
		return delegate.getContentLengthLong();
	}

	@Override
	public String getContentType() {
		return delegate.getContentType();
	}

	@Override
	public String getContentEncoding() {
		return delegate.getContentEncoding();
	}

	@Override
	public long getExpiration() {
		return delegate.getExpiration();
	}

	@Override
	public long getDate() {
		return delegate.getDate();
	}

	@Override
	public long getLastModified() {
		return delegate.getLastModified();
	}

	@Override
	public int getHeaderFieldInt(String name, int defaultValue) {
		return delegate.getHeaderFieldInt(name, defaultValue);
	}

	@Override
	public long getHeaderFieldLong(String name, long defaultValue) {
		return delegate.getHeaderFieldLong(name, defaultValue);
	}

	@Override
	public Object getContent() throws IOException {
		return delegate.getContent();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object getContent(Class[] classes) throws IOException {
		return delegate.getContent(classes);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

	@Override
	public void setDoInput(boolean doinput) {
		delegate.setDoInput(doinput);
	}

	@Override
	public boolean getDoInput() {
		return delegate.getDoInput();
	}

	@Override
	public void setDoOutput(boolean dooutput) {
		delegate.setDoOutput(dooutput);
	}

	@Override
	public boolean getDoOutput() {
		return delegate.getDoOutput();
	}

	@Override
	public void setAllowUserInteraction(boolean allowuserinteraction) {
		delegate.setAllowUserInteraction(allowuserinteraction);
	}

	@Override
	public boolean getAllowUserInteraction() {
		return delegate.getAllowUserInteraction();
	}

	@Override
	public void setUseCaches(boolean usecaches) {
		delegate.setUseCaches(usecaches);
	}

	@Override
	public boolean getUseCaches() {
		return delegate.getUseCaches();
	}

	@Override
	public void setIfModifiedSince(long ifmodifiedsince) {
		delegate.setIfModifiedSince(ifmodifiedsince);
	}

	@Override
	public long getIfModifiedSince() {
		return delegate.getIfModifiedSince();
	}

	@Override
	public boolean getDefaultUseCaches() {
		return delegate.getDefaultUseCaches();
	}

	@Override
	public void setDefaultUseCaches(boolean defaultusecaches) {
		delegate.setDefaultUseCaches(defaultusecaches);
	}


	@Override
	public int hashCode() {
		return Objects.hash(delegate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof HttpsURLConnectionImpl))
			return false;
		HttpsURLConnectionImpl other = (HttpsURLConnectionImpl) obj;
		return Objects.equals(delegate, other.delegate);
	}

	@Override
	public void setConnectTimeout(int timeout) {
		delegate.setConnectTimeout(timeout);
	}

	@Override
	public int getConnectTimeout() {
		return delegate.getConnectTimeout();
	}

	@Override
	public void setReadTimeout(int timeout) {
		delegate.setReadTimeout(timeout);
	}

	@Override
	public int getReadTimeout() {
		return delegate.getReadTimeout();
	}

	@Override
	public void setFixedLengthStreamingMode(int contentLength) {
		delegate.setFixedLengthStreamingMode(contentLength);
	}

	@Override
	public void setFixedLengthStreamingMode(long contentLength) {
		delegate.setFixedLengthStreamingMode(contentLength);
	}

	@Override
	public void setChunkedStreamingMode(int chunklen) {
		delegate.setChunkedStreamingMode(chunklen);
	}
}
