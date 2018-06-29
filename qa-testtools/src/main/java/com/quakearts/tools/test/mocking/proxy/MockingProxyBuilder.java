package com.quakearts.tools.test.mocking.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.tools.test.mocking.EmptyMockedImplementation;
import com.quakearts.tools.test.mocking.EmptyVoidMockedImplementation;
import com.quakearts.tools.test.mocking.MockedImplementation;
import com.quakearts.tools.test.mocking.VoidMockedImplementation;

public class MockingProxyBuilder<T> {
	Class<T> mockedClass;
	
	private Map<String, MockedImplementation> contexts = new ConcurrentHashMap<>();
	
	private MockingProxyBuilder(Class<T> mockedClass) {
		if(!mockedClass.isInterface())
			throw new IllegalArgumentException("Mocking Proxy Builder only works on interfaces");
			
		this.mockedClass = mockedClass;
	}
	
	public static<T> MockingProxyBuilder<T> createMockingInvocationHandlerFor(Class<T> mockedClass) {
		return new MockingProxyBuilder<T>(mockedClass);
	}
	
	public MethodBuilder mock(String methodSignature) {
		return new MethodBuilder(methodSignature);
	}
	
	public class MethodBuilder {
		String methodSignature;

		private MethodBuilder(String methodSignature) {
			this.methodSignature = methodSignature;
		}

		public MockingProxyBuilder<T> with(MockedImplementation implementation) {
			return store(implementation);
		}
		
		public MockingProxyBuilder<T> withVoidMethod(VoidMockedImplementation implementation) {
			return store(implementation);
		}
	
		
		public MockingProxyBuilder<T> withVoidEmptyMethod(EmptyVoidMockedImplementation implementation) {
			return store(implementation);
		}
	
		public MockingProxyBuilder<T> withEmptyMethod(EmptyMockedImplementation implementation) {
			return store(implementation);
		}
	
		private MockingProxyBuilder<T> store(MockedImplementation implementation) {
			contexts.put(methodSignature, implementation);
			return MockingProxyBuilder.this;
		}
	}

	public T thenBuild() {
		InvocationHandler handler = new MockingInvocationHandler(mockedClass, contexts);
		return mockedClass.cast(Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mockedClass}, handler));
	}
}
