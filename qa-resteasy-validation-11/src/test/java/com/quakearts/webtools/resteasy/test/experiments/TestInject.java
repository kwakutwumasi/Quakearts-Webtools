package com.quakearts.webtools.resteasy.test.experiments;

import javax.validation.Valid;

import com.quakearts.webtools.resteasy.validation.test.helpers.TestParameter;

public interface TestInject {
	void processTestParameter(@Valid TestParameter parameter);
}