package com.quakearts.tools.test.generator.bean.collections;

import java.util.SortedSet;
import java.util.TreeSet;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({SortedSet.class, TreeSet.class})
public final class SortedSetFactory implements CollectionFactory {
	@Override
	public TreeSet<?> createNewCollection() {
		return new TreeSet<>();
	}
}