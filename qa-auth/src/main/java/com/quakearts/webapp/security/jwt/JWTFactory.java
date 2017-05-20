package com.quakearts.webapp.security.jwt;

public class JWTFactory {
	private JWTFactory() {
	}
	
	private static JWTFactory instance = new JWTFactory();
	
	public static JWTFactory getInstance() {
		return instance;
	}
	
//	public JWTSigner getSigner(String algorithm, Map<String, ?> options){
//		
//	}
//	
//	public JWTVerifier getVerifier(String algorithm, Map<String, ?> options){
//		
//	}
}
