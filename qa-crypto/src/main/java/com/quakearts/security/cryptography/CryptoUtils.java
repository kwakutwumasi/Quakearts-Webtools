package com.quakearts.security.cryptography;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CryptoUtils {
	private static InitialContext icx = startInitialContext();

    public static InitialContext getInitialContext() {
		return icx;
	}

	private static InitialContext startInitialContext(){
		InitialContext icx;
		try {
			icx = new InitialContext();
			return icx;
		} catch (NamingException e) {
			return null;
		}
    }

}
