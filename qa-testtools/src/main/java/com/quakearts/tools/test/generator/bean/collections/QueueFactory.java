package com.quakearts.tools.test.generator.bean.collections;

import java.util.LinkedList;
import java.util.Queue;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({Queue.class, LinkedList.class})
public final class QueueFactory implements CollectionFactory {
	@Override
	public LinkedList<?> createNewCollection() {
		return new LinkedList<>();
	}
}