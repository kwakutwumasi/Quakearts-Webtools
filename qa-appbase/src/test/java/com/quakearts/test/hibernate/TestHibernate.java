/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.test.hibernate;

//import static org.junit.Assert.*;

import org.junit.Test;

import com.quakearts.appbase.Main;

public class TestHibernate {

//	@Test
//	public void testName() throws Exception {
	public static void main(String[] args){
		try {
			Main.main(new String[]{"com.quakearts.test.hibernate.TestHibernateMainBean","-dontwaitinmain"});
		} catch (IllegalStateException e) {
		}
	}
	
}
