package com.quakearts.tools.test.generator;

import java.util.Collection;

public interface Generator<T> {

	T generateRandom();
	Collection<T> generateRandom(int size);
	Generator<?> useField(String fieldName);
}