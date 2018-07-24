package com.quakearts.utilities;

import java.util.List;

/**Holds name-values pairs of inputed commands
 * @author kwakutwumasi-afriyie
 *
 */
public interface CommandParameter {
	String DEFAULT = "com.quakearst.utilities.DEFAULT";
	/**Getter for name
	 * @return the parameter name
	 */
	String getName();
	/**Getter for value. This is a short cut for {@link #getValues()}.get(0);
	 * @return the parameter value
	 */
	String getValue();
	/**Getter for a list of values
	 * @return the parameter values as a {@linkplain List}
	 */
	List<String> getValues();
	/**Setter for value. Adds the values to an underlying list of values
	 * @param value the value to add
	 */
	void setValue(String value);
}
