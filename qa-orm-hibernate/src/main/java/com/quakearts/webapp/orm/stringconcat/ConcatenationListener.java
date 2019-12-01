package com.quakearts.webapp.orm.stringconcat;

@FunctionalInterface
public interface ConcatenationListener {
	void notify(Class<?> beanClass, Object instance, String value, String trimmed);
}
