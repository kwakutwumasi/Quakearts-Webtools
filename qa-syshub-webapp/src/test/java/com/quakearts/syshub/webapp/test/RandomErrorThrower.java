package com.quakearts.syshub.webapp.test;

import java.util.Random;

import com.quakearts.syshub.exception.ProcessingException;

public class RandomErrorThrower {
	private Random random = new Random();
	
	protected void throwErrorOrNot() throws ProcessingException {
		if(Math.abs(random.nextInt()%10) == 1) {
			throw new ProcessingException("Random Exception");
		}
	}
	
	protected Random getRandom() {
		return random;
	}
}
