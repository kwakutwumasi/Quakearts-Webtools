package com.quakearts.test;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@TestingInterceptor @Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TestInterceptor {
	
	@Inject TestInject testInject;
	
	@AroundInvoke
	public Object testInvoke(InvocationContext context) throws Exception{
		System.out.println("Intercepting...");
		System.out.println(testInject.sayHello());
		Object object = context.proceed();
		System.out.println("Intercepted!");
		
		return object;
	}
}
