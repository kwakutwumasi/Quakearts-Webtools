package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({Double.class, double.class})
public final class DoubleGenerator extends DoubleNumberGenerator<Double> {

	@Override
	public Double generateRandom() {
		return generateRandomDouble();
	}

	@Override
	protected double getMinDouble() {
		return min!=null?min:0;
	}

	@Override
	protected double getMaxDouble() {
		return max!=null?max:0;
	}

	@Override
	protected Double getFromString(String string) {
		return Double.valueOf(string);
	}
	
	@Override
	protected Double getFromDouble(Double number) {
		return number;
	}
}
