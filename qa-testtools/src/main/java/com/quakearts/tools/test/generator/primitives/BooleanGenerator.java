package com.quakearts.tools.test.generator.primitives;

import com.quakearts.tools.test.generator.GeneratorBase;
import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({boolean.class, Boolean.class})
public class BooleanGenerator extends GeneratorBase<Boolean> {

	@Override
	public Boolean generateRandom() {
		return random.nextInt(2)==1?Boolean.TRUE:Boolean.FALSE;
	}

}
