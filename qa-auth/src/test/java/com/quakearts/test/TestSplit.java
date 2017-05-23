package com.quakearts.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSplit {

	@Test
	public void test() {
		byte[] token = "1234567.1234567.1234567".getBytes();
		int splitPoint = scan(token);
		
		byte[] payload = new byte[splitPoint];
		byte[] signature = new byte[token.length-splitPoint-1];
		
		System.arraycopy(token, 0, payload, 0, payload.length);
		System.arraycopy(token, payload.length+1, signature, 0, signature.length);
		
		System.out.println(new String(payload));
		System.out.println(payload.length);
		System.out.println(new String(signature));
		System.out.println(signature.length);
	}
	
	private int scan(byte[] token) {
		int lastPostionOfSeparator = -1;
		int currPos = 0;
		int pointCount = 0;
		for(byte c:token){
			if(c == '.'){
				lastPostionOfSeparator = currPos;
				pointCount++;
			}
			currPos++;
		}
		
		if(pointCount != 2)
			fail();
			
		return lastPostionOfSeparator;
	}
}
