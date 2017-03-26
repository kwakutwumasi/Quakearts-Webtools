package com.quakearts.webapp.beansupport.emtpyhandlerimpl;

import java.util.Collection;

import com.quakearts.webapp.beansupport.emtpyhandler.BeanEmptyHandler;

public class CollectionEmptyHandler implements BeanEmptyHandler<Collection<?>> {

	@Override
	public boolean isEmpty(Collection<?> list) {
		return list.isEmpty();
	}

}
