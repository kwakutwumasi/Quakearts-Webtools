package com.quakearts.rest.client;

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.quakearts.rest.client.exception.HttpClientException;

public abstract class HttpObjectClient extends HttpClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = -395107746119069730L;

	/**Implementers can call this method to convert an Object instance to a String, and configure response processing
	 * @param <T>
	 * @param verb {@link HttpVerb} the HTTP verb to use in the request
	 * @param template the HTTP file to request. Internally {@link java.text.MessageFormat} is used to process the template. See documentation on how to create a template
	 * @param requestValue the instance of an Object to convert to String
	 * @param contentType a shortcut for adding an HTTP Content-Type header for content (if present)
	 * @param responseClass the class to convert the http response bytes to
	 * @param requestHeaders a {@link Map} of a {@link List} of {@link String}s of additional header elements to pass to the request
	 * @param parameters the parameters to pass to the {@link java.text.MessageFormat#format(String, Object...)} method to process the template
	 * @return the HTTP response converted to the specified class
	 * @throws IOException if there is an error during communication
	 * @throws HttpClientException if a parameter or process fails
	 */
	protected <T> T execute(HttpVerb verb, String template, Object requestValue, String contentType,
			Class<T> responseClass, Map<String, List<String>> requestHeaders, Object... parameters)
			throws IOException, HttpClientException {
		return getHttpResponseUsing(template, requestValue != null ? writeValueAsString(requestValue) : null,
				contentType, verb, requestHeaders, parameters).thenCoerceTo(responseClass);
	}

	/**Implementers can call this method to convert an Object instance to a String, and configure response processing
	 * @param <T>
	 * @param verb {@link HttpVerb} the HTTP verb to use in the request
	 * @param template the HTTP file to request. Internally {@link java.text.MessageFormat} is used to process the template. See documentation on how to create a template
	 * @param requestValue the instance of an Object to convert to String
	 * @param contentType a shortcut for adding an HTTP Content-Type header for content (if present)
	 * @param responseClass the class to convert the http response bytes to
	 * @param parameters the parameters to pass to the {@link java.text.MessageFormat#format(String, Object...)} method to process the template
	 * @return the HTTP response converted to the specified class
	 * @throws IOException if there is an error during communication
	 * @throws HttpClientException if a parameter or process fails
	 */
	protected <T> T execute(HttpVerb verb, String template, Object requestValue, String contentType,
			Class<T> responseClass, Object... parameters)
			throws IOException, HttpClientException {
		return execute(verb, template, requestValue, contentType, responseClass, null, parameters);
	}

	/**Implementers can call this method to convert an Object instance to a String, and configure response processing.
	 * This method calls through to the {@link #execute(HttpVerb, String, Object, String, Class, Map, Object...)} method
	 * as a shortcut for HTTP GET request
	 * @param <T>
	 * @param template the HTTP file to request. Internally {@link java.text.MessageFormat} is used to process the template. See documentation on how to create a template
	 * @param responseClass the class to convert the http response bytes to
	 * @param requestHeaders a {@link Map} of a {@link List} of {@link String}s of additional header elements to pass to the request
	 * @param parameters the parameters to pass to the {@link java.text.MessageFormat#format(String, Object...)} method to process the template
	 * @return the HTTP response converted to the specified class
	 * @throws IOException if there is an error during communication
	 * @throws HttpClientException if a parameter or process fails
	 */
	protected <T> T executeGet(String template, Class<T> responseClass, 
			Map<String, List<String>> requestHeaders, Object... parameters)
			throws IOException, HttpClientException {
		return execute(HttpVerb.GET, template, null, null, responseClass, requestHeaders, parameters);
	}
	
	/**Implementers can call this method to convert an Object instance to a String, and configure response processing.
	 * This method calls through to the {@link #execute(HttpVerb, String, Object, String, Class, Map, Object...)} method
	 * as a shortcut for HTTP GET request
	 * @param <T>
	 * @param template the HTTP file to request. Internally {@link java.text.MessageFormat} is used to process the template. See documentation on how to create a template
	 * @param responseClass the class to convert the http response bytes to
	 * @param parameters the parameters to pass to the {@link java.text.MessageFormat#format(String, Object...)} method to process the template
	 * @return the HTTP response converted to the specified class
	 * @throws IOException if there is an error during communication
	 * @throws HttpClientException if a parameter or process fails
	 */
	protected <T> T executeGet(String template, Class<T> responseClass, Object... parameters)
			throws IOException, HttpClientException {
		return executeGet(template, responseClass, null, parameters);
	}
	
	/**Implement this method to return a String representation of the request value as required
	 * for the HTTP request
	 * @param requestValue
	 * @return the requestValue converted to a String
	 * @throws HttpClientException there is processing error
	 */
	protected abstract String writeValueAsString(Object requestValue) throws HttpClientException;

	private HttpResponseBuilder getHttpResponseUsing(String template, String requestValue, String contentType,
			HttpVerb verb, Map<String, List<String>> requestHeaders, Object... parameters)
			throws IOException, HttpClientException {
		HttpResponse httpResponse = sendRequest(withTemplate(template, parameters), requestValue, verb, contentType,
				requestHeaders);
		if (httpResponse.getHttpCode() < 200 || httpResponse.getHttpCode() > 299) {
			throw nonSuccessResponseUsing(httpResponse);
		}

		return newResponseBuilder(httpResponse);
	}

	private HttpResponseBuilder newResponseBuilder(HttpResponse httpResponse){
		return new HttpResponseBuilder(httpResponse);
	}

	private String withTemplate(String template, Object... parameters) {
		return parameters.length > 0 ? format(template, parameters) : template;
	}

	protected String encode(String parameter) throws UnsupportedEncodingException {
		return URLEncoder.encode(parameter, "UTF-8");
	}

	/**Implement this method to throw an appropriate {@link HttpClientException} or a subclass
	 * @param httpResponse
	 * @return the HttpClientException or subclass
	 */
	protected abstract HttpClientException nonSuccessResponseUsing(HttpResponse httpResponse);
	
	protected class HttpResponseBuilder {
		
		private HttpResponse httpResponse;

		public HttpResponseBuilder(HttpResponse httpResponse) {
			this.httpResponse = httpResponse;
		}
		
		protected <T> T thenCoerceTo(Class<T> targetClass)
			throws HttpClientException {
			return createConverter(targetClass).thenConvert(httpResponse);
		}
	}
	
	@FunctionalInterface
	protected interface Converter<R> {
		public R thenConvert(HttpResponse response) 
				throws HttpClientException;
	}
	
	/**Create a Converter that can take any arbitrary class and convert the HTTP response byte to that class
	 * @param <R>
	 * @param targetClass the class to convert the HTTP response to
	 * @return
	 */
	protected abstract<R> Converter<R> createConverter(Class<R> targetClass);
}
