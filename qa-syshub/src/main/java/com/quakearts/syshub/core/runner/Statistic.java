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
package com.quakearts.syshub.core.runner;

/**The statistic to display in the agent monitor
 * @author kwakutwumasi-afriyie
 *
 */
public class Statistic {
	private String name;
	private Object value;

	public Statistic(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	/**Getter for name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**Setter for name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**Getter for the statistic value
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	/**Setter for the statistic value
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
