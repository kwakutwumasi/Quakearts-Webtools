package com.quakearts.webtools.test;

import java.lang.reflect.Field;

import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosBeanDatasourceProviderSpiImpl;
import com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;

/**Base for all tests classes
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class AppBaseTest {
	protected static void createServices() {
		if(JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi()!=null)
			clearInstanceVariable(JavaNamingDirectorySpiFactory.getInstance(), "javaNamingDirectorySpi");
		
		JavaNamingDirectorySpiFactory.getInstance()
			.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName());
		
		if(JavaTransactionManagerSpiFactory.getInstance().getJavaTransactionManagerSpi()!=null)
			clearInstanceVariable(JavaTransactionManagerSpiFactory.getInstance(), "transactionManagerSpi");

		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName());

		if(ContextDependencySpiFactory.getInstance().getContextDependencySpi()!=null)
			clearInstanceVariable(ContextDependencySpiFactory.getInstance(), "dependencySpi");
		
		ContextDependencySpiFactory.getInstance()
			.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		
		if(DataSourceProviderSpiFactory.getInstance().getDataSourceProviderSpi()!=null)
			clearInstanceVariable(DataSourceProviderSpiFactory.getInstance(), "dataSourceProviderSpi");
		
		DataSourceProviderSpiFactory.getInstance()
			.createDataSourceProviderSpi(AtomikosBeanDatasourceProviderSpiImpl.class.getName());

		if(EmbeddedWebServerSpiFactory.getInstance().getEmbeddedWebServerSpi()!=null)
			clearInstanceVariable(EmbeddedWebServerSpiFactory.getInstance(), "webServerSpi");
		
		EmbeddedWebServerSpiFactory.getInstance()
				.createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());

	}
	
	private static void clearInstanceVariable(Object object, String fieldName) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for(Field field: declaredFields)
        {
            if(fieldName.equals(field.getName())){
	            field.setAccessible(true);
	            try {
					field.set(object, null);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException("Unable to clear field variables");
				}
            }
        }
     }
}
