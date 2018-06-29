package test.junit;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.*;

import org.junit.BeforeClass;
import org.junit.Test;
import com.quakearts.tools.test.mocking.proxy.MockingProxyBuilder;
import test.junit.beans.TestInterface;

public class TestMockingProxy {

	private static TestInterface proxy;
	
	@BeforeClass
	public static void createProxy() throws Exception {
		proxy = MockingProxyBuilder.createMockingInvocationHandlerFor(TestInterface.class)
				.mock("voidEmptyMethod").withVoidEmptyMethod(()->{
				}).mock("voidMethod(int,String,byte[],Double[])").withVoidMethod((arguments)->{
					assertThat(arguments.get(0), is(1));
					assertThat(arguments.get(1), is("test1"));
					assertThat(arguments.get(2), is(new byte[] {0x01b, 0x02b}));
					assertThat(arguments.get(3), is(new Double[] {1d, 2d}));
					assertThat(arguments.getMockedClass() == TestInterface.class, is(true));
				}).mock("emptyMethod").withEmptyMethod(()->{
					return "test2";
				}).mock("method(InputStream)").with((arguments)->{
					InputStream stream = arguments.get(0);
					if(stream == null)
						throw new IOException();
					
					return "test3";
				}).thenBuild();
	}
	
	@Test
	public void testProxy() throws Exception {
		proxy.voidEmptyMethod();
		proxy.voidMethod(1, "test1", new byte[] {0x01b, 0x02b}, new Double[] {1d, 2d});
		assertThat(proxy.emptyMethod(), is("test2"));
		assertThat(proxy.method(new ByteArrayInputStream("".getBytes())), is("test3"));
	}
	
	@Test(expected=IOException.class)
	public void testProxyException() throws Exception {
		proxy.method(null);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testNotMocked() throws Exception {
		proxy.notMocked();
	}
}
