package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

@FunctionalInterface
public interface EmptyMockedImplementation extends MockedImplementation {
	Object mockEmpty();
	default Object mock(MockingArguments arguments) {
		throw new UnsupportedOperationException("Implementation does not support mock call");
	}
}
