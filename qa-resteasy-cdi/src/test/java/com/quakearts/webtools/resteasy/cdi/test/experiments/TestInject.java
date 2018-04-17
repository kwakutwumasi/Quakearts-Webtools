package com.quakearts.webtools.resteasy.cdi.test.experiments;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.quakearts.webtools.resteasy.cdi.test.helpers.TestParameter;

public interface TestInject {
	void sayHello();
	void testTransaction();
	TestParameter pullFromInputStream(InputStream in) throws IOException;
	void pushToOutputStream(TestParameter parameter, OutputStream out) throws IOException;
}