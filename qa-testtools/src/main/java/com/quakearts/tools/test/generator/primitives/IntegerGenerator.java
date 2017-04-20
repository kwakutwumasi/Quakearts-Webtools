package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({Integer.class, int.class})
public final class IntegerGenerator extends IntegerNumberGenerator<Integer> {

	@Override
	public Integer generateRandom() {
		return generateRandomInteger();
	}

	@Override
	protected int getMinInt() {
		return min!=null?min:0;
	}

	@Override
	protected int getMaxInt() {
		return max!=null?max:0;
	}
	
	@Override
	protected Integer getFromString(String string) {
		return Integer.valueOf(string);
	}
	
	@Override
	protected Integer getFromDouble(Double number) {
		return number.intValue();
	}
}
