package com.quakearts.approvalengine.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetUtil {
	private SetUtil() {}
	
	@SafeVarargs
	public static <T> Set<T> asSet(T...elements){
		return new HashSet<>(Arrays.asList(elements));
	}
}
