package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

@FunctionalInterface
public interface EmptyVoidMockedImplementation extends MockedImplementation {
	void mockEmptyVoid() throws Throwable;
	default Object mock(MockingArguments arguments) {
		throw new UnsupportedOperationException("Implementation does not support mock call");
	}
}
