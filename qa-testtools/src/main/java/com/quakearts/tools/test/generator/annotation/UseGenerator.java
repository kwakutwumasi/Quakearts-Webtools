package com.quakearts.tools.test.generator.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.quakearts.tools.test.generator.Generator;

@Retention(RUNTIME)
@Target(TYPE)
public @interface UseGenerator {
	Class<? extends Generator<?>> value();
}
