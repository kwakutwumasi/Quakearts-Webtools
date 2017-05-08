/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.tools.test.generator.primitives;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import com.quakearts.tools.test.generator.Generator;
import com.quakearts.tools.test.generator.GeneratorBase;
import com.quakearts.tools.test.generator.annotation.Generates;
import com.quakearts.tools.test.generator.exception.GeneratorException;
import com.quakearts.tools.test.generator.primitives.configuration.AnnotationPropertyConsumer;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

@Generates(Date.class)
public final class DateGenerator extends GeneratorBase<Date> implements AnnotationPropertyConsumer {

	private static Properties properties = new Properties();

	static {
		InputStream in = DateGenerator.class.getClassLoader().getResourceAsStream("random.dates");
		if (in != null) {
			try {
				properties.load(in);
			} catch (IOException e) {
				throw new RuntimeException("Unable to load random.strings");
			}
		}
	}

	private int maxYear = 2099, minYear = 1970, minMonth = 0, maxMonth=11,
			maxDay = 31, minDay = 1, maxHour = 23, minHour = 0;

	@Override
	public DateGenerator useField(String fieldName) {
		super.useField(fieldName);
		setUp();
		return this;
	}
	
	public DateGenerator useMaxYear(int maxYear) {
		if(maxYear >= minYear)
			this.maxYear = maxYear;
		return this;
	}

	public DateGenerator useMinYear(int minYear) {
		if(maxYear >= minYear)
			this.minYear = minYear;
		return this;
	}

	public DateGenerator useMaxMonth(int maxMonth) {
		if(0<=maxMonth && maxMonth <=11 && maxMonth >= minMonth)
			this.maxMonth = maxMonth;
		return this;
	}

	public DateGenerator useMinMonth(int minMonth) {
		if(0<=minMonth && minMonth <=11 && maxMonth >= minMonth)
			this.minMonth = minMonth;
		return this;
	}

	public DateGenerator useMaxDay(int maxDay) {
		if(1<=maxDay && maxDay <=31 && maxDay >= minDay)
			this.maxDay = maxDay;
		return this;
	}

	public DateGenerator useMinDay(int minDay) {
		if(1<=minDay && minDay <=31 && maxDay >= minDay)
			this.minDay = minDay;
		return this;
	}

	public DateGenerator useMaxHour(int maxHour) {
		if(1<=maxHour && maxHour <=31 && maxHour >= minHour)
			this.maxHour = maxHour;
		return this;
	}

	public DateGenerator useMinHour(int minHour) {
		if(1<=minHour && minHour <=31 && maxHour >= minHour)
			this.minHour = minHour;
		return this;
	}

	protected int random(int min, int max){
		if(max==min)
			return max;
		
		int value = random.nextInt(max+1);
		
		if(value < min)
			value= (value %(max - min)) + min;
							
		return value;
	}
	
	@Override
	public Date generateRandom() {		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(random(minYear, maxYear), 
				random(minMonth, maxMonth), 
				random(minDay, maxDay), 
				random(minHour, maxHour),
				random.nextInt(59), 
				random.nextInt(59));

		return cal.getTime();
	}

	private void setUp(){
		if(exists("minYear"))
			minYear = get("minYear");
		
		if(exists("maxYear"))
			maxYear= get("maxYear");

		if(exists("minMonth"))
			minMonth = get("minMonth");

		if(exists("maxMonth"))
			maxMonth = get("maxMonth");

		if(exists("minDay"))
			minDay = get("minDay");

		if(exists("maxDay"))
			maxDay = get("maxDay");

		if(exists("minHour"))
			minHour = get("minHour");

		if(exists("maxHour"))
			maxHour = get("maxHour");

	}
	
	private boolean exists(String suffix){
		return properties.containsKey(fieldName+"."+suffix);
	}
	
	private int get(String suffix){
		try {
			return Integer.parseInt(properties.getProperty(fieldName+"."+suffix));
		} catch (NumberFormatException e) {
			throw new GeneratorException("Unable to parse "+properties.getProperty(fieldName+"."+suffix)+" as int.", e);
		}
	}

	@Override
	public Generator<?> configureFromAnnotations(GenerateWith generatorProperty) {
		if (generatorProperty.maxDay() > 0)
			maxDay = generatorProperty.maxDay();

		if (generatorProperty.maxHour() > 0)
			maxHour = generatorProperty.maxHour();
		
		if (generatorProperty.maxMonth() > 0)
			maxMonth = generatorProperty.maxMonth();
		
		if (generatorProperty.maxYear() > 0)
			maxYear = generatorProperty.maxYear();
		
		if (generatorProperty.minDay() > 0)
			minDay = generatorProperty.minDay();
		
		if (generatorProperty.minHour() > 0)
			minHour = generatorProperty.minHour();
		
		if (generatorProperty.minMonth() > 0)
			minMonth = generatorProperty.minMonth();
		
		if (generatorProperty.minYear() > 0)
			minYear = generatorProperty.minYear();

		return this;
	}
}
