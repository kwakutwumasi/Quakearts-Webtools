package com.quakearts.tools.test.generator.primitives;

public abstract class DoubleNumberGenerator<T extends Number> extends NumberGenerator<T> {
	protected abstract double getMinDouble();

	protected abstract double getMaxDouble();

	protected double generateRandomDouble(){
		double number = random.nextDouble();
		if(max==null && min==null){
			return random.nextDouble();
		} else if(max==null) {
			if(getMinDouble()<0)
				number *= -1;

			number = (number * getMinDouble()) + getMinDouble();
			
			return number;
		} else if (min==null) {
			if(getMaxDouble()<0)
				number *= -1;

			number = getMaxDouble() * number;
			
			return number;
		} else {
			double difference = getMaxDouble() - getMinDouble();
			number *= difference;
			number += getMinDouble();
			
			if(getMaxDouble()<0)
				number *= -1;
			
			return number;
		}
	}

	@Override
	protected boolean isValid(T max, T min) {
		return max.doubleValue() > min.doubleValue();
	}
}
