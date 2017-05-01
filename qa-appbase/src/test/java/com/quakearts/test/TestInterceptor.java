package com.quakearts.test;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@TestingInterceptor @Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TestInterceptor {
	
	@AroundInvoke
	public Object testInvoke(InvocationContext context) throws Exception{
		System.out.println("Intercepting...");
		Object object = context.proceed();
		System.out.println("Intercepted!");
		
		return object;
	}
}
