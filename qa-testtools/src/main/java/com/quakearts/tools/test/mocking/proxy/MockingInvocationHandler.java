package com.quakearts.tools.test.mocking.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import com.quakearts.tools.test.mocking.EmptyMockedImplementation;
import com.quakearts.tools.test.mocking.EmptyVoidMockedImplementation;
import com.quakearts.tools.test.mocking.MockedImplementation;
import com.quakearts.tools.test.mocking.VoidMockedImplementation;
import com.quakearts.tools.test.mocking.helper.MockingArguments;

public class MockingInvocationHandler implements InvocationHandler {

	private Map<String, MockedImplementation> contexts;
	private Class<?> mockedClass;
	
	MockingInvocationHandler(Class<?> mockedClass, Map<String, MockedImplementation> contexts) {
		this.mockedClass = mockedClass;
		this.contexts = contexts;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		MockedImplementation implementation = contexts.get(method.getName());
		if(implementation == null)
			implementation = contexts.get(toSignature(method));
		
		if(implementation == null)
			throw new UnsupportedOperationException("Method "+toSignature(method)
			+" for interface "+mockedClass.getName()
			+" has not been specified for proxy");
				
		if(implementation instanceof EmptyMockedImplementation) {
			return ((EmptyMockedImplementation)implementation).mockEmpty();
		} else if(implementation instanceof EmptyVoidMockedImplementation) {			
			((EmptyVoidMockedImplementation)implementation).mockEmptyVoid();
		} else if(implementation instanceof VoidMockedImplementation) {			
			((VoidMockedImplementation)implementation).mockVoid(new MockingArguments(args, mockedClass));
		} else {			
			return implementation.mock(new MockingArguments(args, mockedClass));
		} 
		
		return null;
	}

	private String toSignature(Method method) {
		StringBuilder builder = new StringBuilder(method.getName());
		builder.append("(");
		
		boolean first=true;
		for(Class<?> arg:method.getParameterTypes()) {
			if(first) {
				first = false;
			} else {
				builder.append(",");
			}
			builder.append(arg.getSimpleName());
		}
		
		return builder.append(")").toString();
	}
}
