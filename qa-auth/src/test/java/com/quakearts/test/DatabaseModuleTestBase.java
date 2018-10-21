package com.quakearts.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.BooleanSupplier;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.quakearts.tools.test.mocking.helper.MockingArguments;
import com.quakearts.tools.test.mocking.proxy.MockingProxyBuilder;

public abstract class DatabaseModuleTestBase extends LoginModuleTest {
	
	static InitialContext context = createContext();
	private static InitialContext createContext() {
		try {
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.quakearts.test.naming.TestInitialContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "com.quakearts.test.naming");
			return new InitialContext();
		} catch (NamingException e) {
			throw new AssertionError("Unable to get IntialContext");
		}
	}
	
	protected static final SQLExceptionThrower DEFAULTTHROWER=()->{};	
	protected static final ArgumentValidator DEFAULTVALIDATOR=a->{};
	
	protected void generateDataSource(String name,
			StringValidator prepareStatementValidator,
			StringValidator setStringValidator, 
			BooleanSupplier next,
			StringSupplier getString) throws NamingException {
		generateDataSource(name, DEFAULTVALIDATOR,prepareStatementValidator, 
				setStringValidator, next, getString);
	}
	
	protected void generateDataSource(String name,
			ArgumentValidator getConnectionValidator,
			StringValidator prepareStatementValidator,
			StringValidator setStringValidator, 
			BooleanSupplier next,
			StringSupplier getString) throws NamingException {
		generateDataSource(name, getConnectionValidator, DEFAULTTHROWER,
				prepareStatementValidator, setStringValidator, next, getString);
	}
	
	protected void generateDataSource(String name,
				ArgumentValidator getConnectionValidator,
				SQLExceptionThrower getConnectionThrower,
				StringValidator prepareStatementValidator,
				StringValidator setStringValidator, 
				BooleanSupplier next,
				StringSupplier getString) throws NamingException {
		ResultSet resultSet = MockingProxyBuilder.createMockingInvocationHandlerFor(ResultSet.class)
				.mock("next").withEmptyMethod(()->{
					return next.getAsBoolean();
				})
				.mock("getString").with(arguments->{ 
					return getString.get(arguments.get(0));
				})
				.mock("close").withVoidEmptyMethod(()->{})
				.thenBuild();
		
		PreparedStatement preparedStatement = MockingProxyBuilder.createMockingInvocationHandlerFor(PreparedStatement.class)
				.mock("setString").withVoidMethod(arguments->{
					setStringValidator.validate(arguments.get(1)); 
				})
				.mock("executeQuery").withEmptyMethod(()->{					
					return resultSet;
				})
				.mock("close").withVoidEmptyMethod(()->{})
				.thenBuild();
		
		Connection connection = MockingProxyBuilder.createMockingInvocationHandlerFor(Connection.class)
				.mock("prepareStatement").with(arguments->{
					prepareStatementValidator.validate(arguments.get(0));
					return preparedStatement;
				})
				.mock("close").withVoidEmptyMethod(()->{})
				.thenBuild();
		
		DataSource dataSource = MockingProxyBuilder
				.createMockingInvocationHandlerFor(DataSource.class)
				.mock("getConnection()").withEmptyMethod(()->{
					getConnectionThrower.throwException();
					return connection;
				})
				.mock("getConnection(String,String)").with(arguments->{
					getConnectionValidator.validateArguments(arguments);
					return connection;
				})
				.thenBuild();
		
		context.bind(name, dataSource);
	}
	
	@FunctionalInterface
	public static interface StringSupplier {
		String get(String name) throws Throwable;
	}
	
	@FunctionalInterface
	public static interface StringValidator {
		void validate(String value) throws Throwable;
	}
	
	@FunctionalInterface
	public static interface SQLExceptionThrower {
		void throwException() throws Throwable;
	}
	
	@FunctionalInterface
	public static interface ArgumentValidator {
		void validateArguments(MockingArguments arguments) throws Throwable;
	}
}