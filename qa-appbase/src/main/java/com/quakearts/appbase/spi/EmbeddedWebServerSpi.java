package com.quakearts.appbase.spi;

public interface EmbeddedWebServerSpi {
	void initiateEmbeddedWebServer();
	void shutdownEmbeddedWebServer();
}
