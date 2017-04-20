package com.quakearts.tools.test.generator.bean;

import java.util.Collection;
import java.util.Collections;

import com.quakearts.tools.test.generator.Generator;

public class NoObjectGenerator<T> implements Generator<T> {

	@Override
	public T generateRandom() {
		return null;
	}

	@Override
	public Collection<T> generateRandom(int size) {
		return Collections.emptyList();
	}

	@Override
	public Generator<T> useField(String fieldName) {
		return this;
	}

}
