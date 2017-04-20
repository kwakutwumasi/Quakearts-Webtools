package com.quakearts.tools.test.generator.bean.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({BlockingQueue.class, DelayQueue.class})
public final class BlockingQueueFactory implements CollectionFactory {
	@Override
	public DelayQueue<?> createNewCollection() {
		return new DelayQueue<>();
	}
}