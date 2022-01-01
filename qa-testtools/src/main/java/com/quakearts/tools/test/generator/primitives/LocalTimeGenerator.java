package com.quakearts.tools.test.generator.primitives;

import java.time.LocalTime;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(LocalTime.class)
public class LocalTimeGenerator extends TemporalGenerator<LocalTime> {

	@Override
	public LocalTimeGenerator useGeneratorProperty(String fieldName) {
		super.useGeneratorProperty(fieldName);
		setUp();
		return this;
	}

	public LocalTimeGenerator useMaxHour(int maxHour) {
		if(1<=maxHour && maxHour <=31 && maxHour >= minHour)
			this.maxHour = maxHour;
		return this;
	}

	public LocalTimeGenerator useMinHour(int minHour) {
		if(1<=minHour && minHour <=31 && maxHour >= minHour)
			this.minHour = minHour;
		return this;
	}
	
	@Override
	public LocalTime generateRandom() {
		return LocalTime.of(random(minHour, maxHour), random(0, 59), random(0, 59));
	}
}
