package com.quakearts.tools.test.generator.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Size {
	int value() default 10;
	int max() default -1;
	int min() default -1;
}
