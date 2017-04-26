package com.quakearts.test;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@TestingInterceptor @Interceptor
public class TestInterceptor {
	@AroundInvoke
	public Object testInvoke(InvocationContext context) throws Exception{
		System.out.println("Intercepting...");
		Object object = context.proceed();
		System.out.println("Intercepted!");
		
		return object;
	}
}
