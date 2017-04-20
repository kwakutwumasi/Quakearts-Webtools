package com.quakearts.tools.test.generator.primitives.configuration;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Calendar;

import com.quakearts.tools.test.generator.primitives.DateGenerator;
import com.quakearts.tools.test.generator.primitives.NumberGenerator;
import com.quakearts.tools.test.generator.primitives.StringRandomGenerator;

@Retention(RUNTIME)
@Target(METHOD)
public @interface GenerateWith {
	/**Maximum number to generate (for {@link NumberGenerator})
	 * @return
	 */
	double max() default -1;
	/**Minimum number to generate (for {@link NumberGenerator})
	 * @return
	 */
	double min() default -1;
	/**Maximum day to generate (for {@link DateGenerator})
	 * Can't be larger than 31
	 * @return
	 */
	int maxDay() default -1;
	/**Maximum hour to generate (for {@link DateGenerator})
	 * Can't be larger than 23
	 * @return
	 */
	int maxHour() default -1;
	/**Maximum month to generate (for {@link DateGenerator})
	 * Can't be larger than 11 (Offset #Months -1). You can use {@link Calendar} constants for months
	 * @return
	 */
	int maxMonth() default -1;
	/**Maximum year to generate (for {@link DateGenerator})
	 * @return
	 */
	int maxYear() default -1;
	/**Minimum day to generate (for {@link DateGenerator})
	 * Can't be smaller than 1
	 * @return
	 */
	int minDay() default -1;
	/**Minimum hour to generate (for {@link DateGenerator})
	 * Can't be smaller than 1
	 * @return
	 */
	int minHour() default -1;
	/**Minimum month to generate (for {@link DateGenerator})
	 * Can't be smaller than 0 (Offset #Months -1). You can use {@link Calendar} constants for months
	 * @return
	 */
	int minMonth() default -1;
	/**Minimum year to generate (for {@link DateGenerator})
	 * @return
	 */
	int minYear() default -1;
	/**Length to generate (for {@link StringRandomGenerator})
	 * @return
	 */
	int length() default -1;
	/**Prefix for all strings (for {@link StringRandomGenerator})
	 * @return
	 */
	String prefix() default "";
	/**Suffix for all strings  (for {@link StringRandomGenerator})
	 * @return
	 */
	String suffix() default "";
	/**List of string values to pick from (for {@link StringRandomGenerator})
	 * @return
	 */
	String[] strings() default {};
	/**The charset to use. One of all, alphaNumeric, alphabet and numeric (for {@link StringRandomGenerator})
	 * @return
	 */
	String charset() default "";
}
