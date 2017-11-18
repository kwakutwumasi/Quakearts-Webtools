package com.quakearts.utilities.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandMetadata {
	String value();
	String descritpion() default "";
	CommandParameterMetadata[] parameters() default {};
	String additionalInfo() default "";
	String examples() default "";
}
