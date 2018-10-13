package com.quakearts.webtools.test.junit;

import static org.junit.Assert.*;

import java.sql.Connection;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.quakearts.webtools.test.DataSourceRunner;
import com.quakearts.webtools.test.helpers.TestInject;

@RunWith(DataSourceRunner.class)
public class DataSourceRunnerTest {

	@Inject
	private TestInject inject;
	
	@Test
	public void testCDINotInjecting() throws Exception {
		assertThat(inject, is(nullValue()));
	}

	@Test
	public void testGetDataSource() throws Exception {
		InitialContext context = new InitialContext();
		
		DataSource dataSource = (DataSource) context.lookup("java:/jdbc/DefaultDS");
		assertThat(dataSource, is(notNullValue()));
		try(Connection connection = dataSource.getConnection()) {
			assertThat(connection, is(notNullValue()));			
		}
	}
}