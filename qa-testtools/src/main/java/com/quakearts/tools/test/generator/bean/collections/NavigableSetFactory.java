package com.quakearts.tools.test.generator.bean.collections;

import java.util.NavigableSet;
import java.util.TreeSet;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor(NavigableSet.class)
public final class NavigableSetFactory implements CollectionFactory {
	@Override
	public TreeSet<?> createNewCollection() {
		return new TreeSet<>();
	}
}