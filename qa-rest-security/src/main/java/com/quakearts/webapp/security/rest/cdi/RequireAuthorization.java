package com.quakearts.webapp.security.rest.cdi;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface RequireAuthorization {
	String[] allow() default {};
	String[] deny() default {};
}
