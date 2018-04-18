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
package com.quakearts.tools.test.mockserver.store.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.tools.test.mockserver.exception.MockServerRuntimeException;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.store.HttpMessageStore;
import com.quakearts.tools.test.mockserver.store.exception.HttpMessageStoreException;
import com.quakearts.tools.test.mockserver.store.fi.HttpMessageStoreQuery;

public class MockServletHttpMessageStore implements HttpMessageStore {

	private File saveLocation;
	private Map<String, HttpRequest> savedRequests;
	private static final String EXT = ".mock";
	
	private MockServletHttpMessageStore() {
		saveLocation = new File("http-messages");
		if(!saveLocation.exists()) {
			saveLocation.mkdir();
		}
	}

	private static HttpMessageStore instance = new MockServletHttpMessageStore();
	
	public static HttpMessageStore getInstance() {
		return instance;
	}
	
	@Override
	public Iterator<HttpRequest> iterator() {
		for(String id:getSavedRequests().keySet()) {
			HttpRequest httpRequest = getSavedRequests().get(id);
			if(httpRequest == NULL) {
				try {
					httpRequest = loadHttpRequest(id);
				} catch (HttpMessageStoreException e) {
					throw new MockServerRuntimeException(e);
				}
				getSavedRequests().put(id, httpRequest);
			}
		}
		
		return getSavedRequests().values().iterator();
	}

	@Override
	public void storeRequest(HttpRequest httpRequest) throws HttpMessageStoreException {
		if(httpRequest instanceof Serializable) {
			String id = sanitizeFileName(httpRequest.getId())+EXT;
			File storageName = new File(saveLocation, id);
			try(FileOutputStream fos = new FileOutputStream(storageName);){
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(httpRequest);
			} catch (IOException e) {
				throw new HttpMessageStoreException("Cannot save httpRequest", e);
			}
			getSavedRequests().put(id, httpRequest);
		} else {
			throw new HttpMessageStoreException("Cannot save httpRequest. HttpRequest implementation must be serializable");
		}
	}

	@Override
	public HttpRequest findRequestIdentifiedBy(String id) throws HttpMessageStoreException {
		id = sanitizeFileName(id)+EXT;
		if(getSavedRequests().containsKey(id)) {
			HttpRequest savedRequest = getSavedRequests().get(id);
			if(savedRequest == NULL) {
				savedRequest = loadHttpRequest(id);
				getSavedRequests().put(id, savedRequest);
			}
			return savedRequest;
		}
		throw new HttpMessageStoreException("Id "+id+" not found");
	}

	@Override
	public IntermediateResult findRequestsMatching(HttpMessageStoreQuery query) throws HttpMessageStoreException {
		IntermediateResultImpl impl = new IntermediateResultImpl();
		for(String id:getSavedRequests().keySet()) {
			HttpRequest httpRequest = getSavedRequests().get(id);
			if(httpRequest == NULL)
				httpRequest = loadHttpRequest(id);
			
			if(query.matches(httpRequest)) {
				impl.foundRequests.add(httpRequest);
			}
		}
		
		return impl;
	}
	
	class IntermediateResultImpl implements IntermediateResult {

		Set<HttpRequest> foundRequests = new HashSet<>();

		@Override
		public HttpRequest[] thenList() {
			return foundRequests.toArray(new HttpRequest[foundRequests.size()]);
		}

		@Override
		public HttpRequest thenFetchOne() {
			if(foundRequests.isEmpty())
				return null;
				
			return foundRequests.iterator().next();
		}
		
	}

	private String sanitizeFileName(String filename) {
		return filename.replaceAll("[^a-zA-Z0-9]", "-");
	}
	
	private HttpRequest loadHttpRequest(String id) throws HttpMessageStoreException {			
		File storageName = new File(saveLocation, id);
		try(FileInputStream fis = new FileInputStream(storageName)) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object httpRequestObject = ois.readObject();
			if (httpRequestObject instanceof HttpRequest) {
				return (HttpRequest) httpRequestObject;
			}
			throw new HttpMessageStoreException("Unable to read stored httpRequest. Object read was not a valid HttpRequest class");
		} catch (IOException | ClassNotFoundException e) {
			throw new HttpMessageStoreException("Unable to read stored httpRequest",e);
		}
	}

	private Map<String, HttpRequest> getSavedRequests() {
		if(savedRequests == null) {
			savedRequests = new ConcurrentHashMap<>();
			for(String file:saveLocation.list()) {
				savedRequests.put(file, NULL);
			}
		}
		return savedRequests;
	}
	
	private static final NullHttpRequest NULL = new NullHttpRequest();
	
	private static class NullHttpRequest implements HttpRequest {

		@Override
		public Collection<HttpHeader> getHeaders() {
			return null;
		}

		@Override
		public String getHeaderValue(String name) {
			return null;
		}

		@Override
		public String getContentEncoding() {
			return null;
		}

		@Override
		public String getContent() throws UnsupportedEncodingException {
			return null;
		}

		@Override
		public String getMethod() {
			return null;
		}

		@Override
		public String getResource() {
			return null;
		}

		@Override
		public HttpResponse getResponse() {
			return null;
		}

		@Override
		public List<String> getURIParameterValue(String name) {
			return null;
		}

		@Override
		public boolean hasParameter(String name) {
			return false;
		}

		@Override
		public byte[] getContentBytes() {
			return null;
		}

		@Override
		public String getId() {
			return null;
		}
		
	}
}
