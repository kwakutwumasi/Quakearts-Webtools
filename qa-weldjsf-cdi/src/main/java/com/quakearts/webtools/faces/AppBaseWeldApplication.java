/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.faces;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.Application;
import javax.faces.application.ApplicationWrapper;

import org.jboss.weld.module.web.el.WeldELContextListener;

public class AppBaseWeldApplication extends ApplicationWrapper {

	private BeanManager beanManager;
    private final Application application;
    private ExpressionFactory expressionFactory;
    private ELResolver elResolver;
	
	public AppBaseWeldApplication(Application application) {
        this.application = application;
        addELContextListener(new WeldELContextListener());
        beanManager = CDI.current().getBeanManager();
        elResolver = beanManager.getELResolver();
        addELResolver(elResolver);
	}

	@Override
	public ExpressionFactory getExpressionFactory() {
		if(expressionFactory == null) {
	        this.expressionFactory = beanManager.wrapExpressionFactory(super.getExpressionFactory());
		}
		return expressionFactory;
	}
	
    @Override
    public Application getWrapped() {
        return application;
    }
}
