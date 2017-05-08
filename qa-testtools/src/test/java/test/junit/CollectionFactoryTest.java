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
package test.junit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Deque;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TransferQueue;

import org.junit.Test;

import com.quakearts.tools.test.generator.bean.collections.AbstractCollectionFactory;
import com.quakearts.tools.test.generator.bean.collections.AbstractListFactory;
import com.quakearts.tools.test.generator.bean.collections.BlockingDequeFactory;
import com.quakearts.tools.test.generator.bean.collections.BlockingQueueFactory;
import com.quakearts.tools.test.generator.bean.collections.CollectionFactoryFinder;
import com.quakearts.tools.test.generator.bean.collections.DequeFactory;
import com.quakearts.tools.test.generator.bean.collections.ListFactory;
import com.quakearts.tools.test.generator.bean.collections.NavigableSetFactory;
import com.quakearts.tools.test.generator.bean.collections.QueueFactory;
import com.quakearts.tools.test.generator.bean.collections.RawCollectionFactory;
import com.quakearts.tools.test.generator.bean.collections.SetFactory;
import com.quakearts.tools.test.generator.bean.collections.SortedSetFactory;
import com.quakearts.tools.test.generator.bean.collections.TransferQueueFactory;
import com.quakearts.tools.test.generator.bootstrap.GeneratorBootstrap;
import static org.hamcrest.core.Is.*;

public class CollectionFactoryTest {

	@Test
	public void testFactoryLoading() {
		GeneratorBootstrap.init();

		assertThat(
				"AbstractCollectionFactory not found", CollectionFactoryFinder.getInstance()
						.findCollectionFactory(AbstractCollection.class).getClass().getName(),
				is(AbstractCollectionFactory.class.getName()));
		assertThat("AbstractListFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(AbstractList.class).getClass().getName(),
				is(AbstractListFactory.class.getName()));
		assertThat("BlockingDequeFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(BlockingDeque.class).getClass().getName(),
				is(BlockingDequeFactory.class.getName()));
		assertThat("BlockingQueueFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(BlockingQueue.class).getClass().getName(),
				is(BlockingQueueFactory.class.getName()));
		assertThat("RawCollectionFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(Collection.class).getClass().getName(),
				is(RawCollectionFactory.class.getName()));
		assertThat("DequeFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(Deque.class).getClass().getName(),
				is(DequeFactory.class.getName()));
		assertThat("ListFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(List.class).getClass().getName(),
				is(ListFactory.class.getName()));
		assertThat("NavigableSetFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(NavigableSet.class).getClass().getName(),
				is(NavigableSetFactory.class.getName()));
		assertThat("QueueFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(Queue.class).getClass().getName(),
				is(QueueFactory.class.getName()));
		assertThat("SetFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(Set.class).getClass().getName(),
				is(SetFactory.class.getName()));
		assertThat("SortedSetFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(SortedSet.class).getClass().getName(),
				is(SortedSetFactory.class.getName()));
		assertThat("TransferQueueFactory not found",
				CollectionFactoryFinder.getInstance().findCollectionFactory(TransferQueue.class).getClass().getName(),
				is(TransferQueueFactory.class.getName()));

	}

}
