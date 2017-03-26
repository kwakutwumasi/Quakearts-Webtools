package com.quakearts.common.exceptionhandler.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Handler {
	Class<? extends Exception> exceptionClass();
	Class<?> relatedClass() default Object.class;
}
