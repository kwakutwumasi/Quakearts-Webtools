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
package com.quakearts.webapp.hibernate;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;

/**Base class for extensions of {@linkplain CurrentSessionContextHelper} that store the {@linkplain Session}
 * object in a {@linkplain Map}.
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class MapBasedCurrentSessionContextHelper extends CurrentSessionContextHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3898361018741844975L;

	public MapBasedCurrentSessionContextHelper(SessionFactoryImplementor implementor) {
		super(implementor);
	}

	protected abstract Map<Object, Object> getContextAttributes();

	protected Session getCurrentSessionFromContextAttributes() {
		return (Session) getContextAttributes().get(getKey());
	}

	protected void removeCurrentSessionFromContextAttributes() {
		getContextAttributes().remove(getKey());
	}

	@Override
	protected void putCurrentSessionInContextAttributes(Session session) {
		getContextAttributes().put(getKey(), session);
	}
}
