package $package;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.MessageFormat;
import javax.net.ServerSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;

public class Main 
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
		
		LOGGER.info(MessageFormat.format("Started in {0, time, ss.S} seconds", System.currentTimeMillis()-start));

		int port = Integer.parseInt(System.getProperty("shutdown.port", "9999"));
		LOGGER.info("Listening for shutdown command on port "+port);

		ServerSocket shutdownSocket;
		
		try {
			shutdownSocket = ServerSocketFactory.getDefault().createServerSocket(port);
		} catch (IOException e) {
			LOGGER.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles getting shutdown hook");
			return;
		}
		
		while(true){
			Socket commandSocket;
			try {
				commandSocket = shutdownSocket.accept();
				if(commandSocket.getInetAddress().isLoopbackAddress()){
					int b = commandSocket.getInputStream().read();
					if(b==0xFA) {
						LOGGER.info("Shutdown called....");
						
						JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi().shutdownJNDIService();;
						ContextDependencySpiFactory.getInstance().getContextDependencySpi().shutDownContextDependency();
						EmbeddedWebServerSpiFactory.getInstance().getEmbeddedWebServerSpi().shutdownEmbeddedWebServer();

						LOGGER.info("Shutdown complete");
						break;
					}
				}
			} catch (IOException e) {
				LOGGER.error("Could not understand message from shutdown command:"+e.getMessage());
			}
		}
    }
}
