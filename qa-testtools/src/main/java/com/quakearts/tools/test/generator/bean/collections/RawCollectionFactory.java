package com.quakearts.tools.test.generator.bean.collections;

import java.util.ArrayList;
import java.util.Collection;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor(Collection.class)
public final class RawCollectionFactory implements CollectionFactory {
	@Override
	public ArrayList<?> createNewCollection() {
		return new ArrayList<>();
	}
}