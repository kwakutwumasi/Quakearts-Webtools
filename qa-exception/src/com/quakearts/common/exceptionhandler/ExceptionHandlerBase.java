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
package com.quakearts.common.exceptionhandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**ExceptionHandler base implementation. May be extended by handlers wishing to take advantage of the convenience methods
 * @author Kwaku Twumasi
 *
 */
public abstract class ExceptionHandlerBase implements ExceptionHandler {
	protected static final Logger loggger = Logger.getLogger("ExceptionHandler");
	
	/**Convenience method to turn parameter array to a map with the key as the class of the parameter. Simplifies getting parameters
	 * @param params The parameter array
	 * @return The map
	 */
	@SuppressWarnings("unchecked")
	protected Map<Class<?>, Object> parametersToMap(Object[] params){
		if(params!=null){
			Map<Class<?>, Object> parameters = new HashMap<>();
			for(Object param:params){
				if(parameters.containsKey(param.getClass())){
					List<Object> list;
					Object object = parameters.get(param.getClass());
					if(object instanceof List){
						list = (List<Object>) object;
					} else {
						list = new ArrayList<>();
						list.add(object);
						parameters.put(param.getClass(), list);
					}
					list.add(param);
				} else {
					parameters.put(param.getClass(), param);
				}
			}
			
			return parameters;
		}
		return null;
	}
	
	/**Convenience method for pulling an object from the provided map, cast to the class specified. Use in conjunction with parametersToMap
	 * @param clazz The expected class
	 * @param parameterMap the parameter map obtained from parametersToMap
	 * @return The object
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getObjectFromMap(Class<T> clazz, Map<Class<?>, Object> parameterMap){
		Object parameter = parameterMap.get(clazz);
		if(parameter != null &&
				clazz.isAssignableFrom(parameter.getClass()))
			return (T) parameter;
		else {
			for(Class<?> key:parameterMap.keySet()){
				if(clazz.isAssignableFrom(key))
					return (T) parameterMap.get(key);
			}
		}
		return null;
	}
	
	/**Convenience method for pulling object arrays from the provided map, cast to an array of the class specified. 
	 * Use in conjuction with parametersToMap
	 * @param clazz The expected class
	 * @param parameterMap the parameter map obtained from parametersToMap
	 * @return The array
	 */
	@SuppressWarnings("unchecked")
	protected <T> T[] getObjectsFromMap(Class<T> clazz, Map<Class<?>, Object> parameterMap){
		Object parameter = parameterMap.get(clazz);
		if(parameter.getClass().isArray())
			try {
				T[] parameters = (T[]) Array.newInstance(clazz, ((Object[])parameter).length);
				System.arraycopy(parameter, 0, parameters, 0, ((Object[])parameter).length);
				return parameters;
			} catch (ClassCastException e) {
			}

		return null;
	}
}
