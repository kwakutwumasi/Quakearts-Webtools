package com.quakearts.tools.test.generator.bean.collections;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({BlockingDeque.class, LinkedBlockingDeque.class})
public final class BlockingDequeFactory implements CollectionFactory {
	@Override
	public LinkedBlockingDeque<?> createNewCollection() {
		return new LinkedBlockingDeque<>();
	}
}