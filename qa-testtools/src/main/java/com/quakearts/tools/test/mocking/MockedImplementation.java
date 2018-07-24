package com.quakearts.tools.test.mocking;

import com.quakearts.tools.test.mocking.helper.MockingArguments;

/**Functional interface for mocking methods that take arguments and have a return type
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface MockedImplementation {
	Object mock(MockingArguments arguments) throws Throwable;
}
