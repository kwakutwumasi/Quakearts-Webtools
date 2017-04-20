package com.quakearts.tools.test.generator.primitives;

public abstract class IntegerNumberGenerator<T extends Number> extends NumberGenerator<T> {
	protected abstract int getMinInt();

	protected abstract int getMaxInt();

	protected int generateRandomInteger(){
		int number = random.nextInt();
		if(max==null && min==null){
			return random.nextInt();
		} else if(max==null){
			if(getMinInt()>=0)
				number = Math.abs(number);

			number = (number % getMinInt())+getMinInt();
			
			return number;
		} else if (min==null){
			if(getMaxInt()>=0)
				number = Math.abs(number);
			else if(getMaxInt()<0)
				number *=-1;

			number = number  % getMaxInt()+1;
			
			return number;
		} else {
			if(getMaxInt() == getMinInt())
				return getMaxInt();
			
			if(getMinInt()>=0)
				number = Math.abs(number);

			number = number % getMaxInt()+1;
			
			if(number < getMinInt())
				number= (number %(getMaxInt() - getMinInt())) + getMinInt();
				
			return number;
		}
	}

	@Override
	protected boolean isValid(T max, T min) {
		return max.intValue() > min.intValue();
	}
}
