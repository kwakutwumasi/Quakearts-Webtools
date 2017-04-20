package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({Short.class, short.class})
public final class ShortGenerator extends IntegerNumberGenerator<Short> {

	@Override
	public Short generateRandom() {
		return (short) generateRandomInteger();
	}

	@Override
	protected int getMinInt() {
		return min != null? min.shortValue(): 0;
	}

	@Override
	protected int getMaxInt() {
		return max != null? max.shortValue(): 0;
	}

	@Override
	protected Short getFromString(String string) {
		return Short.valueOf(string);
	}
	
	@Override
	protected Short getFromDouble(Double number) {
		return number.shortValue();
	}
}
