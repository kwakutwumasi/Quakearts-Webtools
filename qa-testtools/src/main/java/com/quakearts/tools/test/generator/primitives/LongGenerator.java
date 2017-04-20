package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({Long.class, long.class})
public final class LongGenerator extends IntegerNumberGenerator<Long> {

	@Override
	public Long generateRandom() {
		return (long) generateRandomInteger();
	}

	@Override
	protected int getMinInt() {
		return min!=null?min.intValue():0;
	}

	@Override
	protected int getMaxInt() {
		return max!=null?max.intValue():0;
	}
	
	@Override
	protected Long getFromString(String string) {
		return Long.valueOf(string);
	}
	
	@Override
	protected Long getFromDouble(Double number) {
		return number.longValue();
	}
}
