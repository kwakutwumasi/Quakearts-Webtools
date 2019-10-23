package $package;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;

public class Startup 
{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public static void main(String[] args)
    {
    	long start = System.currentTimeMillis();
		JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName());
		ContextDependencySpiFactory.getInstance().createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
		
		JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi().initiateJNDIServices();
		ContextDependencySpiFactory.getInstance().getContextDependencySpi().initiateContextDependency();
		EmbeddedWebServerSpiFactory.getInstance().getEmbeddedWebServerSpi().initiateEmbeddedWebServer();
		String startupTimeLog = MessageFormat.format("Started in {0, time, ss.S} seconds", System.currentTimeMillis()-start);
		LOGGER.info(startupTimeLog);
    }
}
