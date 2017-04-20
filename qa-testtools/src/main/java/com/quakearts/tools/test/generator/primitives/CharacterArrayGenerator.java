package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(char[].class)
public final class CharacterArrayGenerator extends StringRandomGenerator<char[]> {

	@Override
	public char[] generateRandom() {
		return generateRandomString().toCharArray();
	}

}
