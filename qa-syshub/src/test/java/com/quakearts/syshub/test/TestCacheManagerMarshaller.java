package com.quakearts.syshub.test;

import javax.enterprise.inject.Alternative;

import org.infinispan.commons.marshall.Marshaller;
import com.quakearts.syshub.core.utils.impl.CacheManagerImpl;

@Alternative
public class TestCacheManagerMarshaller extends CacheManagerImpl {

	Marshaller getMarshaller() {
		return createMarshaller();
	}

}
