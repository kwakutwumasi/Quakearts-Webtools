package com.quakearts.syshub.core.metadata.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.quakearts.syshub.core.metadata.NameGenerator;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ConfigurationProperty {
	String value() default "";
	boolean required();
	ParameterType type();
	Class<? extends NameGenerator> generatorClass();
	String pattern();
	String description();
	String friendlyName();
	boolean password();
	Class<?> assignableType();

}
