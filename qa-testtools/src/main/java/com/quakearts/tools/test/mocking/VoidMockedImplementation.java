package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

/**Functional interface for mocking methods that parameters and have no return type
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface VoidMockedImplementation extends MockedImplementation {
	void mockVoid(MockingArguments arguments) throws Throwable;
	default Object mock(MockingArguments arguments) {
		throw new UnsupportedOperationException("Implementation does not support mock call");
	}
}
