package com.quakearts.rest.client.net.http;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class ResponseCacheImpl extends ResponseCache {

	@Override
	public CacheResponse get(URI uri, String requestMethod, Map<String, List<String>> requestHeaders) throws IOException {

		return null;
	}

	@Override
	public CacheRequest put(URI uri, URLConnection conn) throws IOException {

		return null;
	}

}
