package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({Byte.class, byte.class})
public final class ByteGenerator extends IntegerNumberGenerator<Byte> {

	@Override
	public Byte generateRandom() {
		return (byte) generateRandomInteger();
	}

	@Override
	protected int getMinInt() {
		return min != null? min:0;
	}

	@Override
	protected int getMaxInt() {
		return max != null? max:0;
	}

	@Override
	protected Byte getFromString(String string) {
		return Byte.valueOf(string);
	}
	
	@Override
	protected Byte getFromDouble(Double number) {
		return number.byteValue();
	}
}
