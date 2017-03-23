package com.quakearts.webapp.beansupport.emtpyhandlerimpl;

import java.util.Map;

import com.quakearts.webapp.beansupport.emtpyhandler.BeanEmptyHandler;

public class MapEmptyHandler implements BeanEmptyHandler<Map<?, ?>> {

	@Override
	public boolean isEmpty(Map<?, ?> value) {
		return value.isEmpty();
	}

}
