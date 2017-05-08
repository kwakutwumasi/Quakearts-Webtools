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
package com.quakearts.webapp.facelets.bootstrap.common;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

public class BootHeaderContainer extends UIComponentBase {
	public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.bootheadcontainer";
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public boolean getRendersChildren() {
		return false;
	}
	
	@Override
	public void encodeAll(FacesContext context) throws IOException {
        for (UIComponent kid : getChildren()) {
            kid.encodeAll(context);
        }
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
	}
}
