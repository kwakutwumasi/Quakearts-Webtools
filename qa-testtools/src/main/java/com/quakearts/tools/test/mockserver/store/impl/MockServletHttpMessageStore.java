package com.quakearts.tools.test.mockserver.store.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.quakearts.tools.test.mockserver.exception.MockServerRuntimeException;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.store.HttpMessageStore;
import com.quakearts.tools.test.mockserver.store.exception.HttpMessageStoreException;
import com.quakearts.tools.test.mockserver.store.fi.HttpMessageStoreQuery;

public class MockServletHttpMessageStore implements HttpMessageStore {

	private File saveLocation;
	private Map<String, HttpRequest> savedRequests = new HashMap<>();
	private static final String EXT = ".mock";
	
	private MockServletHttpMessageStore() {
		saveLocation = new File("http-messages");
		if(!saveLocation.exists()) {
			saveLocation.mkdir();
		} else {
			for(String file:saveLocation.list()) {
				savedRequests.put(file, null);
			}
		}
	}

	private static HttpMessageStore instance = new MockServletHttpMessageStore();
	
	public static HttpMessageStore getInstance() {
		return instance;
	}
	
	@Override
	public Iterator<HttpRequest> iterator() {
		for(String id:savedRequests.keySet()) {
			HttpRequest httpRequest = savedRequests.get(id);
			if(httpRequest == null) {
				try {
					httpRequest = loadHttpRequest(id);
				} catch (HttpMessageStoreException e) {
					throw new MockServerRuntimeException(e);
				}
				savedRequests.put(id, httpRequest);
			}
		}
		
		return savedRequests.values().iterator();
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
			savedRequests.put(id, httpRequest);
		} else {
			throw new HttpMessageStoreException("Cannot save httpRequest. HttpRequest implementation must be serializable");
		}
	}

	@Override
	public HttpRequest findRequestIdentifiedBy(String id) throws HttpMessageStoreException {
		if(savedRequests.containsKey(id)) {
			HttpRequest savedRequest = savedRequests.get(id);
			if(savedRequest == null) {
				savedRequest = loadHttpRequest(id);
				savedRequests.put(id, savedRequest);
			}
			return savedRequest;
		}
		throw new HttpMessageStoreException("Id "+id+" not found");
	}

	@Override
	public IntermediateResult findRequestsMatching(HttpMessageStoreQuery query) throws HttpMessageStoreException {
		IntermediateResultImpl impl = new IntermediateResultImpl();
		for(String id:savedRequests.keySet()) {
			HttpRequest httpRequest = savedRequests.get(id);
			if(httpRequest == null)
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
}
