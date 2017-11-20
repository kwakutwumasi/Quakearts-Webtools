package com.quakearts.utilities.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandParameterMetadata {
	String value();
	String alias() default "";
	String format() default "";
	String description() default "";
	boolean required() default false;
	String linkedParameters() default "";
	boolean canOmitName() default false;
}
