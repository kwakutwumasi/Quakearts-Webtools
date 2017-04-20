package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(String.class)
public final class StringGenerator extends StringRandomGenerator<String> {

	@Override
	public String generateRandom() {
		return generateRandomString();
	}

}
