package test;

import java.net.URI;
import java.net.URL;
import java.security.URIParameter;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class LoginContextTest {

	public static void main(String[] args) {
		final CallbackHandler handler = (c)->{};
		try {
			URL resource = Thread.currentThread().getContextClassLoader().
                    getResource("login.config");
            URI uri = resource.toURI();
            final Configuration jaasConfig = Configuration.getInstance("JavaLoginConfig", 
            		new URIParameter(uri));
            
            //Warm up
            for(int count = 0; count<1000000;count++){
            	useloginContext(handler, jaasConfig);
            	usedirect(handler);
            	useInternalContext(handler, jaasConfig);
            }
    		
            long start = System.nanoTime();
            useloginContext(handler, jaasConfig);
            long stop = System.nanoTime();
            System.out.println("useloginContext: "+(stop-start)+" ns");
            start = System.nanoTime();
            usedirect(handler);
            stop = System.nanoTime();
            System.out.println("usedirect: "+(stop-start)+" ns");
            start = System.nanoTime();
        	useInternalContext(handler, jaasConfig);
            stop = System.nanoTime();
            System.out.println("useInternalContext: "+(stop-start)+" ns");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void useloginContext(CallbackHandler handler, Configuration jaasConfig) throws Exception{
		javax.security.auth.login.LoginContext context = new javax.security.auth.login.LoginContext("TestConfig", null, handler, jaasConfig);
		context.login();
	}
	
	static void useInternalContext(CallbackHandler handler, Configuration jaasConfig) throws Exception{
		com.quakearts.webapp.security.rest.context.LoginContext context = new com.quakearts.webapp.security.rest.context.LoginContext("TestConfig", null, handler, jaasConfig);
		context.login();
	}
	
	static void usedirect(CallbackHandler handler) throws Exception {
		TestLoginModule module = new TestLoginModule();
		module.initialize(new Subject(), handler, new HashMap<>(), new HashMap<>());
		module.login();
	}

	public static class TestLoginModule implements LoginModule {

		@Override
		public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
				Map<String, ?> options) {
		}

		@Override
		public boolean login() throws LoginException {
			return true;
		}

		@Override
		public boolean commit() throws LoginException {
			return true;
		}

		@Override
		public boolean abort() throws LoginException {
			return true;
		}

		@Override
		public boolean logout() throws LoginException {
			return true;
		}
		
	}
}