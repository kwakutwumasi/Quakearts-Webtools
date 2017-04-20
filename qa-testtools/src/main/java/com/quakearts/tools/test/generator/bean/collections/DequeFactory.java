package com.quakearts.tools.test.generator.bean.collections;

import java.util.ArrayDeque;
import java.util.Deque;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({Deque.class, ArrayDeque.class})
public final class DequeFactory implements CollectionFactory {
	@Override
	public ArrayDeque<?> createNewCollection() {
		return new ArrayDeque<>();
	}
}