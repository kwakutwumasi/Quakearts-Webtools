package com.quakearts.appbase.test.experiments;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.quakearts.appbase.test.helpers.TestParameter;

public interface TestInject {
	void sayHello();
	void testTransaction();
	TestParameter pullFromInputStream(InputStream in) throws IOException;
	void pushToOutputStream(TestParameter parameter, OutputStream out) throws IOException;
}