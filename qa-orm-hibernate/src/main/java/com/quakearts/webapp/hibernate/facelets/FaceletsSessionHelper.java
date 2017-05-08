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
package com.quakearts.webapp.hibernate.facelets;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.engine.spi.SessionFactoryImplementor;

import com.quakearts.webapp.hibernate.MapBasedCurrentSessionContextHelper;

public class FaceletsSessionHelper extends MapBasedCurrentSessionContextHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2348883328509028661L;

	public FaceletsSessionHelper(SessionFactoryImplementor implementor) {
		super(implementor);
	}

	@Override
	protected Map<Object, Object> getContextAttributes() {
		return FacesContext.getCurrentInstance().getAttributes();
	}
}
