package com.quakearts.tools.test.generator.primitives;

import java.time.LocalDate;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(LocalDate.class)
public class LocalDateGenerator extends TemporalGenerator<LocalDate> {

	@Override
	public LocalDateGenerator useGeneratorProperty(String fieldName) {
		super.useGeneratorProperty(fieldName);
		setUp();
		return this;
	}
	
	public LocalDateGenerator useMaxYear(int maxYear) {
		if(maxYear >= minYear)
			this.maxYear = maxYear;
		return this;
	}

	public LocalDateGenerator useMinYear(int minYear) {
		if(maxYear >= minYear)
			this.minYear = minYear;
		return this;
	}

	public LocalDateGenerator useMaxMonth(int maxMonth) {
		if(0<=maxMonth && maxMonth <=11 && maxMonth >= minMonth)
			this.maxMonth = maxMonth;
		return this;
	}

	public LocalDateGenerator useMinMonth(int minMonth) {
		if(0<=minMonth && minMonth <=11 && maxMonth >= minMonth)
			this.minMonth = minMonth;
		return this;
	}

	public LocalDateGenerator useMaxDay(int maxDay) {
		if(1<=maxDay && maxDay <=31 && maxDay >= minDay)
			this.maxDay = maxDay;
		return this;
	}

	public LocalDateGenerator useMinDay(int minDay) {
		if(1<=minDay && minDay <=31 && maxDay >= minDay)
			this.minDay = minDay;
		return this;
	}

	@Override
	public LocalDate generateRandom() {
		return LocalDate.of(random(minYear, maxYear), random(minMonth, maxMonth), 
				random(minDay, maxDay));
	}
}
