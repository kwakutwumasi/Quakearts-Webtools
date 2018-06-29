package test.junit.beans;

import java.io.IOException;
import java.io.InputStream;

public interface TestInterface {
	void voidEmptyMethod();
	void voidMethod(int integer, String string, byte[] bytes, Double[] doubles);
	String emptyMethod();
	String method(InputStream in) throws IOException;
	int notMocked();
}
