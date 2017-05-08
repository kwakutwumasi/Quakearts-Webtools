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

import java.util.List;

import com.quakearts.tools.test.generator.annotation.CollectionType;

public class Parent {
	private Person person;
	private List<Parent> children;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@CollectionType(Parent.class)
	public List<Parent> getChildren() {
		return children;
	}

	public void setChildren(List<Parent> children) {
		this.children = children;
	}

}
