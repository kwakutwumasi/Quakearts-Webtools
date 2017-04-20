package com.quakearts.tools.test.generator.bean.collections;

import java.util.AbstractList;
import java.util.ArrayList;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor(AbstractList.class)
public final class AbstractListFactory implements CollectionFactory {
	@Override
	public ArrayList<?> createNewCollection() {
		return new ArrayList<>();
	}
}