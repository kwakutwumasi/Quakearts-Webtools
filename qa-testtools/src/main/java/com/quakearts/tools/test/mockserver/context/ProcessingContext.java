package com.quakearts.tools.test.mockserver.context;

import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;

public interface ProcessingContext {
	HttpRequest getHttpRequest() throws MockServerProcessingException;
	void sendHttpError(int errorCode) throws MockServerProcessingException;
	void sendHttpError(int errorCode, String message) throws MockServerProcessingException;
	void addHeader(String name, String value) throws MockServerProcessingException;
	void writeToOutput(int httpCode, String outputToWrite) throws MockServerProcessingException;
	void sendResponse(HttpResponse response) throws MockServerProcessingException;
	boolean responseSent();
}
