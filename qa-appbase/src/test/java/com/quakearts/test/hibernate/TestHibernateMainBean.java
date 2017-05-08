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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.quakearts.appbase.cdi.annotation.TransactionParticipant;
import com.quakearts.appbase.cdi.annotation.TransactionParticipant.TransactionType;
import com.quakearts.webapp.hibernate.HibernateSessionDataStore;
import com.quakearts.webapp.orm.DataStore;

public class TestHibernateMainBean {
		
	@TransactionParticipant(TransactionType.SINGLETON)
	public void init(){
		DataStore store = new HibernateSessionDataStore();
		
		TestModel model = new TestModel();
		model.setId(1);
		model.setTestBoolean(true);
		model.setTestDouble(80d);
		model.setTestName("Test");
		
		store.save(model);
		store.flushBuffers();
		
		assertThat(model.id, is(1));
	}


}
