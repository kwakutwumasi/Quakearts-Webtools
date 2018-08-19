package com.quakearts.appbase.test;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static org.hamcrest.core.Is.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosBeanDatasourceProviderSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.test.helpers.HideResourceClassLoader;
import com.quakearts.appbase.test.helpers.rules.ClassLoaderRule;
import com.quakearts.appbase.test.helpers.rules.SystemEnvironmentRule;

public class TestAtomikosBeanDatasourceSpiImpl {

	private HideResourceClassLoader classLoader = new HideResourceClassLoader();
	
	@Rule
	public ClassLoaderRule classLoaderRule = new ClassLoaderRule()
		.replaceClassLoaderWith(classLoader);
	@Rule
	public SystemEnvironmentRule systemEnvironmentRule = new SystemEnvironmentRule();
	
	@BeforeClass
	public static void setDerbyShutdown() {
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException e) {
			}
		}));
	}
		
	@Before
	public void startJNDI() {
		JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();		
	}
	
	@After
	public void stopJNDI() throws IllegalArgumentException, IllegalAccessException {
		TestAppBaseMainStartup.clearInstanceVariables();
	}
	
	@Test
	public void testAtomikosBeanDatasourceSpiImpl() throws Exception {
		classLoader.setResourcePattern(null);
		DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi(AtomikosBeanDatasourceProviderSpiImpl.class.getName());
		DataSourceProviderSpi providerSpi = DataSourceProviderSpiFactory.getInstance().getDataSourceProviderSpi();
		providerSpi.initiateDataSourceSpi();
	
		assertThat(providerSpi.getDataSource("TestDB") != null, is(true));
		
		DataSource dataSource = providerSpi.getDataSource("TestDB");
		try(Connection con = dataSource.getConnection()){
		} catch (Exception e) {
			fail("unable to obtain a connection: "+e.getMessage());
		}
		
		assertThat(providerSpi.getDataSource("TestInternalDB") != null, is(true));
		
		dataSource = providerSpi.getDataSource("TestInternalDB");
		AtomikosDataSourceBean bean = (AtomikosDataSourceBean) dataSource;
		assertThat(bean.getBorrowConnectionTimeout(), is(2));
		assertThat(bean.getLoginTimeout(), is(3));
		assertThat(bean.getMaintenanceInterval(), is(120));
		assertThat(bean.getMaxIdleTime(), is(130));
		assertThat(bean.getMaxLifetime(), is(140));
		assertThat(bean.getMaxPoolSize(), is(20));
		assertThat(bean.getMinPoolSize(), is(2));
		assertThat(bean.getConcurrentConnectionValidation(), is(false));
		assertThat(bean.getReapTimeout(), is(120));
		assertThat(bean.getTestQuery(), is("SELECT 1"));
			
		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			dataSource =(DataSource) initialContext.lookup("java:/jdbc/TestDB");
			
			assertThat(dataSource != null, is(true));
	
			dataSource =(DataSource) initialContext.lookup("java:/jdbc/TestInternalDB");			
	
			assertThat(dataSource != null, is(true));
		} catch (NamingException | ClassCastException e) {
			fail("unable to obtain a connection: "+e.getMessage());
		}
		
		providerSpi.shutDownDataSourceProvider();
		providerSpi.shutDownDataSourceProvider();
	}

	@Test
	public void testAtomikosBeanDatasourceSpiImplWithEnvironmentVariables() throws Exception {
		classLoader.setResourcePattern(".*\\.json");
		systemEnvironmentRule.set("ds.xa.createDatabase").as("create")
							 .set("ds.xa.databaseName").as("./testenvironmentdb")
							 .set("ds.datasource.class").as("org.apache.derby.jdbc.EmbeddedXADataSource")
							 .set("ds.datasource.name").as("TestEnvironmentDB");
				
		try {
			AtomikosBeanDatasourceProviderSpiImpl providerSpi = new AtomikosBeanDatasourceProviderSpiImpl("environmentdatasource");
			providerSpi.initiateDataSourceSpi();
			assertThat(providerSpi.getDataSource("TestEnvironmentDB") != null, is(true));
			providerSpi.shutDownDataSourceProvider();
		} finally {
			deleteFile(new File("environmentdatasource"));
		}		
	}

	@Test
	public void testAtomikosBeanDatasourceSpiImplWithAppConfiguration() throws Exception {
		classLoader.setResourcePattern(".*default\\.ds\\.json");
		try {
			AtomikosBeanDatasourceProviderSpiImpl providerSpi = new AtomikosBeanDatasourceProviderSpiImpl("appdatasource");
			providerSpi.initiateDataSourceSpi();
			assertThat(providerSpi.getDataSource("TestAppDB") != null, is(true));		
			providerSpi.shutDownDataSourceProvider();
		} finally {
			deleteFile(new File("appdatasource"));
		}
	}
	
	private void deleteFile(File file) {
		if(file.exists()) {
			if(file.isDirectory()) {
				for(File subFile:file.listFiles()) {
					deleteFile(subFile);
				}
			}
			file.delete();
		}
	}
}
