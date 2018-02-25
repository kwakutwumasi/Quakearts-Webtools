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
package test.junit;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.quakearts.tools.test.generator.primitives.DateGenerator;
import com.quakearts.tools.test.generator.primitives.DoubleGenerator;
import com.quakearts.tools.test.generator.primitives.EnumRandomGenerator;
import com.quakearts.tools.test.generator.primitives.FloatGenerator;
import com.quakearts.tools.test.generator.primitives.IntegerGenerator;
import com.quakearts.tools.test.generator.primitives.NumberGenerator;
import com.quakearts.tools.test.generator.primitives.StringGenerator;
import com.quakearts.tools.test.generator.primitives.StringRandomGenerator;

import test.junit.beans.CustomerClass;

public class PrimitivesTest {

	@Test
	public void testEnumGenerator() throws Exception {
		EnumRandomGenerator<CustomerClass> generator = new EnumRandomGenerator<>(CustomerClass.class);
		
		for(int count=0; count < 20; count++){
			try {
				generator.generateRandom();
			} catch (Throwable e) {
				fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles generating Enum");
			}
		}
	}
	
	@Test
	public void testIntegerGenerator() {
		IntegerGenerator generator = new IntegerGenerator();

		try {
			generator.generateRandom();
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating random number");
		}
	}

	@Test
	public void testIntegerGeneratorBuilder() throws Exception {
		for(int count=0; count < 1000; count++){
			NumberGenerator<Integer> generatorMax = new IntegerGenerator().max(10);
			int i = generatorMax.generateRandom();
			assertThat("Greater than 10 "+i, i <= 10, is(true));
	
			NumberGenerator<Integer> generatorMin = new IntegerGenerator().min(50);
			i = generatorMin.generateRandom();
			assertThat("Less than 50 "+i, i >= 50, is(true));;
	
			NumberGenerator<Integer> generatorMinMax = new IntegerGenerator().min(5).max(7);
			i = generatorMinMax.generateRandom();
			assertThat("Not between 5 and 7 "+i, 5 <= i && i <= 7, is(true));;
		}
	}

	@Test
	public void testNumberFieldUse() throws Exception {
		for(int count=0; count < 1000; count++){
			NumberGenerator<Integer> generatorMax = new IntegerGenerator().useField("inttestmax");
			int i = generatorMax.generateRandom();
			assertThat("Greater than 10 "+i, i <= 10, is(true));;
	
			NumberGenerator<Integer> generatorMin = new IntegerGenerator().useField("inttestmin");
			i = generatorMin.generateRandom();
			assertThat("Less than 50 "+i, i >= 50, is(true));;
	
			NumberGenerator<Integer> generatorMinMax = new IntegerGenerator().useField("inttestmaxmin");
			i = generatorMinMax.generateRandom();
			assertThat("Not between 5 and 7 "+i, 5 <= i && i <= 7, is(true));;
		}
	}

	@Test
	public void testDoubleGenerator() throws Exception {
		DoubleGenerator generator = new DoubleGenerator();
		
		try {
			generator.generateRandom();
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating random number");
		}
		
		for(int count=0; count < 1000; count++){
			NumberGenerator<Double> generatorMax = new DoubleGenerator().max(10.5);
			double i = generatorMax.generateRandom();
			assertThat("Greater than 10.5 "+i, i < 10.5, is(true));;
	
			NumberGenerator<Double> generatorMin = new DoubleGenerator().min(51.5);
			i = generatorMin.generateRandom();
			assertThat("Less than 51.5 "+i, i > 51.5, is(true));;
	
			NumberGenerator<Double> generatorMinMax = new DoubleGenerator().min(1d).max(2d);
			i = generatorMinMax.generateRandom();
			assertThat("Not between 1 and 2 "+i, 1d < i && i < 2d, is(true));;
		}
	}
	
	@Test
	public void testFloatGenerator() throws Exception {
		FloatGenerator generator = new FloatGenerator();
		
		try {
			generator.generateRandom();
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating random number");
		}
		
		for(int count=0; count < 1000; count++){
			NumberGenerator<Float> generatorMax = new FloatGenerator().max(10.5f);
			float i = generatorMax.generateRandom();
			assertThat("Greater than 10 "+i, i < 10.5, is(true));;
	
			NumberGenerator<Float> generatorMin = new FloatGenerator().min(51.5f);
			i = generatorMin.generateRandom();
			assertThat("Less than 51.5 "+i, i > 51.5, is(true));;
	
			NumberGenerator<Float> generatorMinMax = new FloatGenerator().min(1f).max(2f);
			i = generatorMinMax.generateRandom();
			assertThat("Not between 1 and 2 "+i, 1f < i && i < 2f, is(true));;
		}
	}
	
	@Test
	public void testStringsGenerator() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator();
	
		try {
			String string = generator.generateRandom();
			
			assertThat("Length is not 10: "+string,string.length()==10, is(true));
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating random string");
		}
		
		for(int count=0; count < 1000; count++){
			generator = new StringGenerator()
					.useLength(50);
			String string = generator.generateRandom();		
			assertThat("Length is not 50: "+string, string.length()==50, is(true));
			
			generator.usePrefix("test");
			string = generator.generateRandom();		
			assertThat("Length is not 54: "+string, string.length()==54, is(true));
			assertThat("Invalid prefix: "+string+" expected 'test'", string.startsWith("test"), is(true));;
	
			generator.useSuffix("end");
			string = generator.generateRandom();		
			assertThat("Length is not 57: "+string, string.length()==57, is(true));
			assertThat("Invalid suffix: "+string+" expected 'end'", string.endsWith("end"), is(true));;
			
			generator = new StringGenerator().useField("stringtest");
			string = generator.generateRandom();
			assertThat("Not found in list: "+string, ("lorem;ipsum;dolor;sit;amet;"
					+ "consectetur;adipiscing;elit;sed;do;eiusmod;"
					+ "tempor;incididunt;ut;labore;et;dolore;magna;aliqua").contains(string), is(true));;
			
			generator = new StringGenerator().useField("stringotherdelim");
			string = generator.generateRandom();
			assertThat("Not found in list: "+string, ("sed ut perspiciatis unde omnis "
					+ "iste natus error sit voluptatem")
					.contains(string) && string.length()<=12, is(true));
			
			generator = new StringGenerator().useField("stringproperties");
			string = generator.generateRandom();
			assertThat("Unexpected: "+string, string.length()==24 
					&& string.endsWith("KR")
					&& string.startsWith("AT"), is(true));

			generator = new StringGenerator().useField("allsymbols");
			string = generator.generateRandom();
			assertThat(string, containsOneOf(string,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKL"
					+ "MNOPQRSTUVWXYZ01234567890!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));

			generator = new StringGenerator().useCharset("all");
			string = generator.generateRandom();
			assertThat(string, containsOneOf(string,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKL"
					+ "MNOPQRSTUVWXYZ01234567890!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));

			generator = new StringGenerator().useField("alphaNumeric");
			string = generator.generateRandom();
			assertThat(string, !containsOneOf(string,"\\!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));

			generator = new StringGenerator().useCharset("alphaNumeric");
			string = generator.generateRandom();
			assertThat(string, !containsOneOf(string,"!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));

			generator = new StringGenerator().useField("numeric");
			string = generator.generateRandom();
			assertThat(string, !containsOneOf(string,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKL"
					+ "MNOPQRSTUVWXYZ!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));

			generator = new StringGenerator().useCharset("numeric");
			string = generator.generateRandom();
			assertThat(string, !containsOneOf(string,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKL"
					+ "MNOPQRSTUVWXYZ!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));
			
			generator = new StringGenerator().useField("alphabet");
			string = generator.generateRandom();
			assertThat(string, !containsOneOf(string,"01234567890!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));

			generator = new StringGenerator().useCharset("alphabet");
			string = generator.generateRandom();
			assertThat(string, !containsOneOf(string,"01234567890!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`~"), is(true));

		}
	}
	
	private boolean containsOneOf(String string, String string2) {
		for(char c:string2.toCharArray()){
			if(string.indexOf(c)!=-1)
				return true;
		}
		
		return false;
	}

	@Test
	public void testDate() throws Exception {
		DateGenerator generator = new DateGenerator();

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(generator.generateRandom());			
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating random date");
		}
		
		assertThat("Invalid year: "+cal.get(Calendar.YEAR), 1970<=cal.get(Calendar.YEAR) && cal.get(Calendar.YEAR)<=2099, is(true));;
		
		for(int count=0; count < 1000; count++){
			generator = new DateGenerator();
			generator.useMinYear(2018);
			Date date = generator.generateRandom();
	
			cal.setTime(date);
			assertThat("Date generated was not before 2018: "+cal.get(Calendar.YEAR), 
					cal.get(Calendar.YEAR)>= 2018, is(true));;
				
			generator = new DateGenerator().useMaxYear(2018);
			date = generator.generateRandom();
	
			cal.setTime(date);
			assertThat("Date generated was not after 2018: "+cal.get(Calendar.YEAR), 
					cal.get(Calendar.YEAR)<= 2018, is(true));;

			generator = new DateGenerator().useMaxYear(2018).useMinYear(2014);
			date = generator.generateRandom();
	
			cal.setTime(date);
			assertThat("Date generated was not after 2018: "+cal.get(Calendar.YEAR), 
					cal.get(Calendar.YEAR)>= 2014 && cal.get(Calendar.YEAR)<= 2018, is(true));;
			
			generator.useMaxMonth(Calendar.FEBRUARY)
				.useMinMonth(Calendar.JANUARY)
				.useMaxDay(5)
				.useMinDay(1)
				.useMaxHour(12)
				.useMinHour(10);
			
			date = generator.generateRandom();
			
			cal.setTime(date);
			assertThat("Date generated was not within parameters: "+date, 
					cal.get(Calendar.YEAR)>= 2014 && cal.get(Calendar.YEAR)<= 2018
					&& cal.get(Calendar.MONTH)>= Calendar.JANUARY && cal.get(Calendar.MONTH)<= Calendar.FEBRUARY
					&& cal.get(Calendar.DATE)>= 1 && cal.get(Calendar.DATE)<= 5
					&& cal.get(Calendar.HOUR_OF_DAY)>= 10 && cal.get(Calendar.HOUR_OF_DAY)<= 12, is(true));;
		}
	}
}
