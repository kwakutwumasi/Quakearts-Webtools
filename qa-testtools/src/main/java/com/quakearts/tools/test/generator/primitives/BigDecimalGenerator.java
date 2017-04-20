package com.quakearts.tools.test.generator.primitives;

import java.math.BigDecimal;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(BigDecimal.class)
public final class BigDecimalGenerator extends DoubleNumberGenerator<BigDecimal> {

	@Override
	public BigDecimal generateRandom() {
		return new BigDecimal(generateRandomDouble());
	}
	
	protected double getMinDouble(){
		return min != null? min.doubleValue():0;
	}

	protected double getMaxDouble(){
		return max != null? max.doubleValue():0;
	}
	
	@Override
	protected BigDecimal getFromString(String string) {
		return new BigDecimal(string);
	}
	
	@Override
	protected BigDecimal getFromDouble(Double number) {
		return new BigDecimal(number);
	}
}
