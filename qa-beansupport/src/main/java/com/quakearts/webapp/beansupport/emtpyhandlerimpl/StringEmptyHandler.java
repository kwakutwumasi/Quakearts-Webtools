package com.quakearts.webapp.beansupport.emtpyhandlerimpl;

import com.quakearts.webapp.beansupport.emtpyhandler.BeanEmptyHandler;

public class StringEmptyHandler implements BeanEmptyHandler<String> {

	@Override
	public boolean isEmpty(String value) {
		return value.isEmpty();
	}

}
