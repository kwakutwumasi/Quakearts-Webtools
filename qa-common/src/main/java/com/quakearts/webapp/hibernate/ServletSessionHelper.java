package com.quakearts.webapp.hibernate;

import java.util.HashMap;
import java.util.Map;


//import javax.faces.context.FacesContext;

import org.hibernate.engine.spi.SessionFactoryImplementor;

public class ServletSessionHelper extends CurrentSessionContextHelper {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9078120922061282997L;

	public ServletSessionHelper(SessionFactoryImplementor implementor) {
		super(implementor);
	}

	private static final ThreadLocal<Map<Object, Object>> threadLocalContext = new ThreadLocal<Map<Object, Object>>(){
		protected Map<Object,Object> initialValue() {
			return new HashMap<>();
		};
	};
	
	protected Map<Object, Object> getContextAttributes(){
		return threadLocalContext.get();
	}
	
}
