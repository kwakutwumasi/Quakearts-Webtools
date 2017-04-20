package com.quakearts.tools.test.generator.bean.collections;

import java.util.HashSet;
import java.util.Set;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({Set.class, HashSet.class})
public final class SetFactory implements CollectionFactory {
	@Override
	public HashSet<?> createNewCollection() {
		return new HashSet<>();
	}
}