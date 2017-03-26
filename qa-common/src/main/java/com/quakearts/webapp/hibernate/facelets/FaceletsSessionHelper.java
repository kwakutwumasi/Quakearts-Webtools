package com.quakearts.webapp.hibernate.facelets;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.engine.spi.SessionFactoryImplementor;

import com.quakearts.webapp.hibernate.CurrentSessionContextHelper;

public class FaceletsSessionHelper extends CurrentSessionContextHelper {

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
