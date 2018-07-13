package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

/**Functional interface for mocking methods that have no arguments
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface EmptyMockedImplementation extends MockedImplementation {
	Object mockEmpty() throws Throwable;
	default Object mock(MockingArguments arguments) {
		throw new UnsupportedOperationException("Implementation does not support mock call");
	}
}
