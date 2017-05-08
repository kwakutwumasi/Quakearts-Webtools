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
package com.quakearts.tools.test.generator.bean.helper;

public class Tuple<C1,C2> {

	private C1 first;
	private C2 second;
	
	public Tuple(C1 first, C2 second) {
		this.first = first;
		this.second = second;
	}

	public Tuple() {
	}
	
	public C1 getFirst() {
		return first;
	}

	public C2 getSecond() {
		return second;
	}
	
	public void setSecond(C2 second) {
		this.second = second;
	}

}
