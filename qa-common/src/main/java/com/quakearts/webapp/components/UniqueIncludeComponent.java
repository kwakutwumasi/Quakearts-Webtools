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
package com.quakearts.webapp.components;

import java.util.Collection;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIViewRoot;
import javax.faces.component.UniqueIdVendor;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

public class UniqueIncludeComponent extends UIComponentBase implements NamingContainer, UniqueIdVendor {

	public static final String COMPONENT_FAMILY = "com.quakearts.webapp.components.unique";

	enum PropertyKeys {
		LASTID
	}

	@Override
	public String createUniqueId(FacesContext context, String seed) {
		Integer i = (Integer) getStateHelper().get(PropertyKeys.LASTID);
		int lastId = ((i != null) ? i : 0);
		getStateHelper().put(PropertyKeys.LASTID, ++lastId);
		return UIViewRoot.UNIQUE_ID_PREFIX + (seed == null ? lastId : seed);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public String getRendererType() {
		return null;
	}

	@Override
	public boolean visitTree(VisitContext context, VisitCallback callback) {
		Collection<String> idsToVisit = context.getSubtreeIdsToVisit(this);
		assert (idsToVisit != null);

		if (!idsToVisit.isEmpty()) {
			return super.visitTree(context, callback);
		}

		if (isVisitable(context)) {
			FacesContext facesContext = context.getFacesContext();
			pushComponentToEL(facesContext, null);

			try {
				VisitResult result = context.invokeVisitCallback(this, callback);
				return (result == VisitResult.COMPLETE);
			} finally {
				popComponentFromEL(facesContext);
			}
		}
		return false;
	}
}
