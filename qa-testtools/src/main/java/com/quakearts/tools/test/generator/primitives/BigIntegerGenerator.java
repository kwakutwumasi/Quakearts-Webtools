package com.quakearts.tools.test.generator.primitives;

import java.math.BigInteger;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(BigInteger.class)
public final class BigIntegerGenerator extends IntegerNumberGenerator<BigInteger> {

	@Override
	public BigInteger generateRandom() {
		return BigInteger.valueOf(generateRandomInteger());
	}

	@Override
	protected int getMinInt() {
		return min!=null? min.intValue(): 0;
	}

	@Override
	protected int getMaxInt() {
		return max!=null? max.intValue(): 0;
	}

	@Override
	protected BigInteger getFromString(String string) {
		return new BigInteger(string);
	}
	
	@Override
	protected BigInteger getFromDouble(Double number) {
		return BigInteger.valueOf(number.longValue());
	}
}
