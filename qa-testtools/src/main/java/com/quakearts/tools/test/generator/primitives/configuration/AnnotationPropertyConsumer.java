package com.quakearts.tools.test.generator.primitives.configuration;

import com.quakearts.tools.test.generator.Generator;

public interface AnnotationPropertyConsumer {
	Generator<?> configureFromAnnotations(GenerateWith generatorProperty);
}
