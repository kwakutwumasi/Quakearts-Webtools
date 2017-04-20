package com.quakearts.tools.test.generator.bean.collections;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;

@CollectionFactoryFor({TransferQueue.class, LinkedTransferQueue.class})
public final class TransferQueueFactory implements CollectionFactory {
	@Override
	public LinkedTransferQueue<?> createNewCollection() {
		return new LinkedTransferQueue<>();
	}
}