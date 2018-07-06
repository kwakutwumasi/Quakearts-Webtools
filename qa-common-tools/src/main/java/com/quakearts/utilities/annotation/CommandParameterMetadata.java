package com.quakearts.utilities.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Annotation for command parameter metadata. Represents a single parameter of the command
 * @author kwakutwumasi-afriyie
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandParameterMetadata {
	/**The parameter name
	 * @return a String of The parameter name
	 */
	String value();
	/**An alias for the parameter name
	 * @return a String of the alias for the parameter name
	 */
	String alias() default "";
	/**The description of the expected format of the parameter value
	 * @return a String of the description of the expected format of the parameter value
	 */
	String format() default "";
	/**The description of the parameter
	 * @return a String of the description of the parameter
	 */
	String description() default "";
	/**A boolean value indicating that the command is required
	 * @return true if the parameter is required
	 */
	boolean required() default false;
	/**A semicolon separated list of parameters required by this parameter 
	 * @return a String of parameters required by this parameter 
	 */
	String linkedParameters() default "";
	/**A boolean value indicating that the parameter name can be omitted.
	 * For use with a single parameter, usually the last parameter and the target of the command
	 * @return true if the parameter name is not required
	 */
	boolean canOmitName() default false;
}
