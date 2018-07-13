package com.quakearts.tools.test.mocking.helper;

/**This class holds the list of arguments passed by the method caller
 * @author kwakutwumasi-afriyie
 *
 */
public class MockingArguments {
	private Object[] args;
	private Class<?> mockedClass;
	
	public MockingArguments(Object[] args, Class<?> mockedClass) {
		this.args = args;
		this.mockedClass = mockedClass;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(int index){
		return (T) args[index];
	}
	
	public Class<?> getMockedClass() {
		return mockedClass;
	}
}
