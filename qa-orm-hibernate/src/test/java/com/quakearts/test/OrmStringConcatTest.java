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
package com.quakearts.test;

import static org.junit.Assert.*;

import javax.persistence.Column;
import javax.persistence.Transient;
import org.junit.Test;

import com.quakearts.webapp.orm.stringconcat.OrmStringConcatUtil;

import static org.hamcrest.core.Is.*;

public class OrmStringConcatTest {

	@Test
	public void testStringConcat() {
		TestBean bean = new TestBean();
		bean.setAnnotatedField("12345678901234567890");
		bean.setNonAnnotatedField("12345678901234567890");
		bean.setNonStringField(300);
		bean.setTransientField("12345678901234567890");
		
		TestBeanChild beanChild = new TestBeanChild();
		beanChild.setAnnotatedField("12345678901234567890");
		beanChild.setNonAnnotatedField("12345678901234567890");
		beanChild.setNonStringField(300);
		beanChild.setTransientField("12345678901234567890");
		beanChild.setYastring("12345678901234567890");		
		
		try {
			OrmStringConcatUtil.trimStrings(bean);
			OrmStringConcatUtil.trimStrings(beanChild);
			
			assertThat(bean.getAnnotatedField(), is("1234567890"));
			assertThat(bean.getNonAnnotatedField(), is("12345"));
			assertThat(bean.getNonStringField(), is(300));
			assertThat(bean.getTransientField(), is("12345678901234567890"));

			assertThat(beanChild.getAnnotatedField(), is("1234567890"));
			assertThat(beanChild.getYastring(), is("1234567890"));
			assertThat(beanChild.getNonAnnotatedField(), is("12345"));
			assertThat(beanChild.getNonStringField(), is(300));
			assertThat(beanChild.getTransientField(), is("12345678901234567890"));
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	public class TestBean {
		@Column(length = 10)
		private String annotatedField;
		private String nonAnnotatedField;
		@Column(length = 1)
		private int nonStringField;
		@Transient
		private String transientField;

		public String getAnnotatedField() {
			return annotatedField;
		}

		public void setAnnotatedField(String annotatedField) {
			this.annotatedField = annotatedField;
		}

		@Column(length = 5)
		public String getNonAnnotatedField() {
			return nonAnnotatedField;
		}

		public void setNonAnnotatedField(String nonAnnotatedField) {
			this.nonAnnotatedField = nonAnnotatedField;
		}

		public int getNonStringField() {
			return nonStringField;
		}

		public void setNonStringField(int nonStringField) {
			this.nonStringField = nonStringField;
		}

		public String getTransientField() {
			return transientField;
		}

		public void setTransientField(String transientField) {
			this.transientField = transientField;
		}
	}
	
	public class TestBeanChild extends TestBean {
		@Column(length=10)
		private String yastring;
		
		public String getYastring() {
			return yastring;
		}
		
		public void setYastring(String yastring) {
			this.yastring = yastring;
		}
	}
}


