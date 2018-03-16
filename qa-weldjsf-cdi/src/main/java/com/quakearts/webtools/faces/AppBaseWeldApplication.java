package com.quakearts.webtools.faces;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.Application;
import javax.faces.application.ApplicationWrapper;

import org.jboss.weld.el.WeldELContextListener;

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
