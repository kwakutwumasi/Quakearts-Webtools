package com.quakearts.appbase.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosBeanDatasourceProviderSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;

public class TestAtomikosBeanDatasourceSpiImpl {

	@Test
	public void testAtomikosBeanDatasourceSpiImpl() throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException e) {
			}
		}));
		
		JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();
		
		try {
			DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi(AtomikosBeanDatasourceProviderSpiImpl.class.getName());
			DataSourceProviderSpi providerSpi = DataSourceProviderSpiFactory.getInstance().getDataSourceProviderSpi();
			providerSpi.initiateDataSourceSpi();
	
			assertThat(providerSpi.getDataSource("TestDB") != null, is(true));
			
			DataSource dataSource = providerSpi.getDataSource("TestDB");
			try(Connection con = dataSource.getConnection()){
			} catch (Exception e) {
				fail("unable to obtain a connection: "+e.getMessage());
			}
			
			InitialContext initialContext;
			try {
				initialContext = new InitialContext();
				dataSource =(DataSource) initialContext.lookup("java:/jdbc/TestDB");
				
				assertThat(dataSource != null, is(true));
			} catch (NamingException | ClassCastException e) {
				fail("unable to obtain a connection: "+e.getMessage());
			}
			
			providerSpi.shutDownDataSourceProvider();
			providerSpi.shutDownDataSourceProvider();
		} finally {
			JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
			new TestAppBaseMainStartup().clearInstanceVariables();
		}
	}

}
