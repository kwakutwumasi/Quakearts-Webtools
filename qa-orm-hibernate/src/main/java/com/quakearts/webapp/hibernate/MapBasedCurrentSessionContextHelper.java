package com.quakearts.webapp.hibernate;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;

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
