/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.resteasy.validation;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.jboss.resteasy.api.validation.SimpleViolationsContainer;

public class AppBaseViolationsContainer extends SimpleViolationsContainer implements Serializable {

	private static final long serialVersionUID = 5310680220754582329L;

	public AppBaseViolationsContainer(Object target) {
		super(target);
	}

	public AppBaseViolationsContainer(Set<ConstraintViolation<Object>> cvs) {
		super(cvs);
	}
}
