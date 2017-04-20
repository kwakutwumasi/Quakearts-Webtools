package com.quakearts.tools.test.generator.bean.collections;

import java.util.ArrayList;
import java.util.List;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({List.class, ArrayList.class})
public final class ListFactory implements CollectionFactory {
	@Override
	public ArrayList<?> createNewCollection() {
		return new ArrayList<>();
	}
}