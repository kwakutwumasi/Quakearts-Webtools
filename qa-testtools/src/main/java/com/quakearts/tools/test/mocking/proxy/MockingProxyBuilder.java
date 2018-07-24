package com.quakearts.tools.test.mocking.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.tools.test.mocking.EmptyMockedImplementation;
import com.quakearts.tools.test.mocking.EmptyVoidMockedImplementation;
import com.quakearts.tools.test.mocking.MockedImplementation;
import com.quakearts.tools.test.mocking.VoidMockedImplementation;

/**Builder class for creating mocked objects.
 * @author kwakutwumasi-afriyie
 *
 * @param <T> the type of interface to mock
 */
public class MockingProxyBuilder<T> {
	Class<T> mockedClass;
	
	private Map<String, MockedImplementation> contexts = new ConcurrentHashMap<>();
	
	private MockingProxyBuilder(Class<T> mockedClass) {
		if(!mockedClass.isInterface())
			throw new IllegalArgumentException("Mocking Proxy Builder only works on interfaces");
			
		this.mockedClass = mockedClass;
	}
	
	/**Create a mocked implementation of the provided class
	 * @param mockedClass the mocked class
	 * @return this object for method chaining
	 */
	public static<T> MockingProxyBuilder<T> createMockingInvocationHandlerFor(Class<T> mockedClass) {
		return new MockingProxyBuilder<T>(mockedClass);
	}
	
	/**Specify a method to mimic
	 * @param methodSignature the name of the method, or the signature in the event that
	 * the method is overloaded. The signature uses a simple format: the name of the method, 
	 * followed by the {@link Class#getSimpleName() simple name} of the parameters, in parentheses 
	 * with no spaces. Ex. this method would have the signature <code>mock(String)</code>
	 * @return a temporary {@linkplain MethodBuilder} object for method chaining
	 */
	public MethodBuilder mock(String methodSignature) {
		return new MethodBuilder(methodSignature);
	}
	
	/**Temporary object for chaining method mocking calls
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public class MethodBuilder {
		String methodSignature;

		private MethodBuilder(String methodSignature) {
			this.methodSignature = methodSignature;
		}

		/**Provide a lambda expression defining the mocked action to perform.
		 * This method should be used for methods that take arguments and have a return type
		 * @param implementation the lambda definition of the mocked method
		 * @return the {@linkplain MockingProxyBuilder} for method chaining
		 */
		public MockingProxyBuilder<T> with(MockedImplementation implementation) {
			return store(implementation);
		}
		
		/**Provide a lambda expression defining the mocked action to perform.
		 * This method should be used for methods that take arguments and have no return type
		 * @param implementation the lambda definition of the mocked method
		 * @return the {@linkplain MockingProxyBuilder} for method chaining
		 */
		public MockingProxyBuilder<T> withVoidMethod(VoidMockedImplementation implementation) {
			return store(implementation);
		}
	
		/**Provide a lambda expression defining the mocked action to perform.
		 * This method should be used for methods that have no arguments and have no return type
		 * @param implementation the lambda definition of the mocked method
		 * @return the {@linkplain MockingProxyBuilder} for method chaining
		 */
		public MockingProxyBuilder<T> withVoidEmptyMethod(EmptyVoidMockedImplementation implementation) {
			return store(implementation);
		}

		/**Provide a lambda expression defining the mocked action to perform.
		 * This method should be used for methods that take no arguments and have a return type
		 * @param implementation the lambda definition of the mocked method
		 * @return the {@linkplain MockingProxyBuilder} for method chaining
		 */
		public MockingProxyBuilder<T> withEmptyMethod(EmptyMockedImplementation implementation) {
			return store(implementation);
		}
	
		private MockingProxyBuilder<T> store(MockedImplementation implementation) {
			contexts.put(methodSignature, implementation);
			return MockingProxyBuilder.this;
		}
	}

	/**Terminal call in the fluid API. Return the mocked object
	 * @return the mocked object
	 */
	public T thenBuild() {
		InvocationHandler handler = new MockingInvocationHandler(mockedClass, contexts);
		return mockedClass.cast(Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mockedClass}, handler));
	}
}
