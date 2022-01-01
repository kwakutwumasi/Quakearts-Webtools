package com.quakearts.tools.test.generator.primitives;

import java.time.LocalDateTime;
import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(LocalDateTime.class)
public class LocalDateTimeGenerator extends TemporalGenerator<LocalDateTime> {

	@Override
	public LocalDateTimeGenerator useGeneratorProperty(String fieldName) {
		super.useGeneratorProperty(fieldName);
		setUp();
		return this;
	}
	
	public LocalDateTimeGenerator useMaxYear(int maxYear) {
		if(maxYear >= minYear)
			this.maxYear = maxYear;
		return this;
	}

	public LocalDateTimeGenerator useMinYear(int minYear) {
		if(maxYear >= minYear)
			this.minYear = minYear;
		return this;
	}

	public LocalDateTimeGenerator useMaxMonth(int maxMonth) {
		if(0<=maxMonth && maxMonth <=11 && maxMonth >= minMonth)
			this.maxMonth = maxMonth;
		return this;
	}

	public LocalDateTimeGenerator useMinMonth(int minMonth) {
		if(0<=minMonth && minMonth <=11 && maxMonth >= minMonth)
			this.minMonth = minMonth;
		return this;
	}

	public LocalDateTimeGenerator useMaxDay(int maxDay) {
		if(1<=maxDay && maxDay <=31 && maxDay >= minDay)
			this.maxDay = maxDay;
		return this;
	}

	public LocalDateTimeGenerator useMinDay(int minDay) {
		if(1<=minDay && minDay <=31 && maxDay >= minDay)
			this.minDay = minDay;
		return this;
	}

	public LocalDateTimeGenerator useMaxHour(int maxHour) {
		if(1<=maxHour && maxHour <=31 && maxHour >= minHour)
			this.maxHour = maxHour;
		return this;
	}

	public LocalDateTimeGenerator useMinHour(int minHour) {
		if(1<=minHour && minHour <=31 && maxHour >= minHour)
			this.minHour = minHour;
		return this;
	}
	
	@Override
	public LocalDateTime generateRandom() {
		return LocalDateTime.of(random(minYear, maxYear), random(minMonth, maxMonth), 
				random(minDay, maxDay), random(minHour, maxHour), 
				random(0, 59), random(0, 59));
	}
}
