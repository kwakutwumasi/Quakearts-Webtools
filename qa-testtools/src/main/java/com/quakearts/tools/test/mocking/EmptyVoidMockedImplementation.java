package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

/**Functional interface for mocking methods that take no parameters and have no return type
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface EmptyVoidMockedImplementation extends MockedImplementation {
	void mockEmptyVoid() throws Throwable;
	default Object mock(MockingArguments arguments) {
		throw new UnsupportedOperationException("Implementation does not support mock call");
	}
}
