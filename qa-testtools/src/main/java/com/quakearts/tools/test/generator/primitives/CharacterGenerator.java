package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({Character.class, char.class})
public final class CharacterGenerator extends StringRandomGenerator<Character> {

	@Override
	public Character generateRandom() {
		String string = generateRandomString();
		if(string.isEmpty())
			return '\0';
		
		return string.toCharArray()[0];
	}

}
