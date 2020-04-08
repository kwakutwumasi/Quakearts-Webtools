package com.quakearts.approvalengine.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AEUtils {
	private static final String EMPTY = "";

	private AEUtils() {}
	
	@SafeVarargs
	public static <T> Set<T> asSet(T...elements){
		return new HashSet<>(Arrays.asList(elements));
	}
	
	public static String toEncodedString(Long...values) {
		if(values.length == 0){
			return EMPTY;
		}
		
		StringBuilder string = new StringBuilder();
		for(Long value:values){
			String hex = Long.toHexString(value).toUpperCase();
			string.append(hex.length()%2==0?EMPTY:"0")
				.append(hex);
		}
		
		return string.toString();
	}
}
