package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

@FunctionalInterface
public interface VoidMockedImplementation extends MockedImplementation {
	void mockVoid(MockingArguments arguments);
	default Object mock(MockingArguments arguments) {
		throw new UnsupportedOperationException("Implementation does not support mock call");
	}
}
