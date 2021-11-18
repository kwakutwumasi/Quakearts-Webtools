package com.quakearts.syshub.test.helper;

import java.util.Collections;
import java.util.List;

import com.quakearts.syshub.core.utils.CacheWhiteListProvider;

public class CacheWhiteListProviderImpl implements CacheWhiteListProvider {

	@Override
	public List<String> getWhiteList() {
		return Collections.singletonList("com.quakearts.syshub.test.helper.*");
	}

}
