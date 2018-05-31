package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

@FunctionalInterface
public interface MockedImplementation {
	Object mock(MockingArguments arguments);
}
