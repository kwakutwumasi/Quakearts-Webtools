/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.appbase.test.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("testRequest")
@RequestScoped
public class TestRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1457271262196005158L;

	public String getHashCode() {
		return Integer.toHexString(hashCode());
	}
}
