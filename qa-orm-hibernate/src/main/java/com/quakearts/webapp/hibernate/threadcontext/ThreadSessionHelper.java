package com.quakearts.webapp.hibernate.threadcontext;

import java.util.HashMap;
import java.util.Map;


//import javax.faces.context.FacesContext;

import org.hibernate.engine.spi.SessionFactoryImplementor;

import com.quakearts.webapp.hibernate.MapBasedCurrentSessionContextHelper;

public class ThreadSessionHelper extends MapBasedCurrentSessionContextHelper {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9078120922061282997L;

	public ThreadSessionHelper(SessionFactoryImplementor implementor) {
		super(implementor);
	}

	private static final ThreadLocal<Map<Object, Object>> threadLocalContext = new InheritableThreadLocal<Map<Object, Object>>(){
		protected Map<Object,Object> initialValue() {
			return new HashMap<>();
		};
	};
	
	protected Map<Object, Object> getContextAttributes(){
		return threadLocalContext.get();
	}
	
}
