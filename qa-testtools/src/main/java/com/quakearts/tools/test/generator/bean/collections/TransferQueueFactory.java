/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
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
