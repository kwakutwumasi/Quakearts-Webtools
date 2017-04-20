package com.quakearts.tools.test.generator.bean.collections;

import java.util.AbstractCollection;
import java.util.ArrayList;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor(AbstractCollection.class)
public final class AbstractCollectionFactory implements CollectionFactory {
	@Override
	public ArrayList<?> createNewCollection() {
		return new ArrayList<>();
	}
}