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
package com.quakearts.appbase.spi.impl.tomcat;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.annotation.HandlesTypes;
import org.apache.catalina.startup.ContextConfig;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class AppBaseContextConfig extends ContextConfig {

	private static final Log log = LogFactory.getLog(ContextConfig.class);

	@Override
	protected void processServletContainerInitializers() {

		List<ServletContainerInitializer> detectedScis;
		try {
			AppBaseWebappServiceLoader<ServletContainerInitializer> loader = new AppBaseWebappServiceLoader<>(context);
			detectedScis = loader.load(ServletContainerInitializer.class);
		} catch (IOException e) {
			log.error(sm.getString("contextConfig.servletContainerInitializerFail", context.getName()), e);
			ok = false;
			return;
		}

		for (ServletContainerInitializer sci : detectedScis) {
			handleServletContainerInitializer(sci);
		}
	}

	private void handleServletContainerInitializer(ServletContainerInitializer sci) {
		initializerClassMap.put(sci, new HashSet<Class<?>>());

		HandlesTypes ht;
		try {
			ht = sci.getClass().getAnnotation(HandlesTypes.class);
			Class<?>[] types = ht == null ? null : ht.value();
			if (types != null)
				for (Class<?> type : types) {
					setHandlesAnnotationsaOrNonAnnotations(type);
					addServletContainerInitializers(sci, type);
				}
		} catch (Exception e) {
			handleException(sci, e);
		}
	}

	private void setHandlesAnnotationsaOrNonAnnotations(Class<?> type) {
		if (type.isAnnotation()) {
			handlesTypesAnnotations = true;
		} else {
			handlesTypesNonAnnotations = true;
		}
	}

	private void addServletContainerInitializers(ServletContainerInitializer sci, Class<?> type) {
		Set<ServletContainerInitializer> scis = typeInitializerMap.computeIfAbsent(type, k -> new HashSet<>());
		scis.add(sci);
	}

	private void handleException(ServletContainerInitializer sci, Exception e) {
		if (log.isDebugEnabled()) {
			log.info(sm.getString("contextConfig.sci.debug", sci.getClass().getName()), e);
		} else {
			log.info(sm.getString("contextConfig.sci.info", sci.getClass().getName()));
		}
	}
}
