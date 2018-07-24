package com.quakearts.utilities.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Annotation for commands. Provides metadata for printing out usage messages
 * @author kwakutwumasi-afriyie
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface CommandMetadata {
	/**The name of the command. Used by {@link com.quakearts.utilities.GenerateBatchFiles} 
	 * to generate the name of the batch file
	 * @return
	 */
	String value();
	/**The command description to print out
	 * @return
	 */
	String description() default "";
	/**A set of parameter metadata to print out
	 * @return a String containing the description
	 */
	CommandParameterMetadata[] parameters() default {};
	/**Additional information, such us expected output, links, etc. Analogous to the 
	 * information printed at the end of *nix Man Pages
	 * @return a String containing the additional information
	 */
	String additionalInfo() default "";
	/**Example commands to help the user. Analogous to the 
	 * information printed at the end of *nix Man Pages
	 * @return a String containing the examples
	 * @return
	 */
	String examples() default "";
}
