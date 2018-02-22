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
package test.junit.beans;

import java.util.Date;

import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public class Note {
	private Date createDate;
	private String value;

	@GenerateWith(maxYear=2017, minYear=2016)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@GenerateWith(strings={"Withdrawal","Deposit", "Transfer","Bill Payment","Mobile Money"})
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
