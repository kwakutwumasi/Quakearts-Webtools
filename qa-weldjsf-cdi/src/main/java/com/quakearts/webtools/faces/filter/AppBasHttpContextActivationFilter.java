package com.quakearts.webtools.faces.filter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jboss.weld.servlet.spi.HttpContextActivationFilter;

import com.quakearts.appbase.Main;

public class AppBasHttpContextActivationFilter implements HttpContextActivationFilter {

	private static final Set<String> resourceExtensions;
	
	static {
		HashSet<String> resourceExtensionsTemp = new HashSet<>();
		resourceExtensionsTemp.add(".png");
		resourceExtensionsTemp.add(".jpg");
		resourceExtensionsTemp.add(".jpeg");
		resourceExtensionsTemp.add(".gif");
		resourceExtensionsTemp.add(".css");
		resourceExtensionsTemp.add(".js");
		resourceExtensionsTemp.add(".html");
		resourceExtensionsTemp.add(".svg");
		resourceExtensionsTemp.add(".eot");
		resourceExtensionsTemp.add(".ttf");
		resourceExtensionsTemp.add(".woff");
		resourceExtensionsTemp.add(".woff2");
		resourceExtensionsTemp.add(".otf");
		
		resourceExtensions = Collections.unmodifiableSet(resourceExtensionsTemp);
	}
	
	public AppBasHttpContextActivationFilter() {
		Main.log.info("AppBasHttpContextActivationFilter started");
	}
	
	@Override
	public void cleanup() {
	}

	@Override
	public boolean accepts(HttpServletRequest request) {
		if(request.getRequestURI() !=null) {
			return !resourceExtensions.stream().parallel().anyMatch((extension)->{
				return request.getRequestURI()
						.trim().endsWith(extension);
			});
		}
		return false;
	}

}
