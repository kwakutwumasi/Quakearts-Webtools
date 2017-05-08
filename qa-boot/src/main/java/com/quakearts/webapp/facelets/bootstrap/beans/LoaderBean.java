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
package com.quakearts.webapp.facelets.bootstrap.beans;

import java.io.Serializable;

public class LoaderBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1599598630373609593L;
	private static final String MAINAJAXLOADER = "qab.mlh";
	private static final String MINIAJAXLOADER = "qab.mnh";
	private static final String OVERLAYAJAXLOADER = "qab.ovh";
	private static final String OVERLAYCLASS = "ajax-container";
	
	
	public String getMainAjaxLoader() {
		return MAINAJAXLOADER;
	}
	
	public String getMiniAjaxLoader() {
		return MINIAJAXLOADER;
	}
	
	public String getOverlayAjaxLoader() {
		return OVERLAYAJAXLOADER;
	}
	
	public String getOverlayClass() {
		return OVERLAYCLASS;
	}
}
