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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.management.ObjectName;
import javax.servlet.ServletContext;

import org.apache.catalina.Context;
import org.apache.catalina.Globals;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Loader;
import org.apache.catalina.util.LifecycleMBeanBase;
import org.apache.tomcat.util.ExceptionUtils;
import org.apache.tomcat.util.modeler.Registry;

import com.quakearts.appbase.exception.ConfigurationException;

public class AppBaseWebAppLoader extends LifecycleMBeanBase implements PropertyChangeListener, Loader {

	private static final String CONTEXTSTRING = ",context=";
	private static final String HOSTSTRING = ",host=";
	private Context context;

	public AppBaseWebAppLoader(Context context) {
		if (context == null)
			throw new ConfigurationException("context cannot be null");
	}

	@Override
	public void backgroundProcess() {
		// Do nothing
	}

	private AppBaseWebappClassLoader classLoader;

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void setContext(Context context) {
		if (getState().isAvailable()) {
			throw new IllegalStateException("Loader is not available for change");
		}

		if (this.context != null) {
			this.context.removePropertyChangeListener(this);
		}

		Context oldContext = this.context;
		this.context = context;
		support.firePropertyChange("context", oldContext, this.context);

		if (this.context != null) {
			this.context.addPropertyChangeListener(this);
		}
	}

	@Override
	public boolean getDelegate() {
		return false;
	}

	@Override
	public void setDelegate(boolean delegate) {
		// Do nothing
	}

	@Override
	public boolean getReloadable() {
		return false;
	}

	@Override
	public void setReloadable(boolean reloadable) {
		// Do nothing
	}

	protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public boolean modified() {
		return false;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// Do nothing
	}

	@Override
	protected String getDomainInternal() {
		return context.getDomain();
	}

	@Override
	protected String getObjectNameKeyProperties() {
		StringBuilder name = new StringBuilder("type=Loader").append(HOSTSTRING).append(context.getParent().getName())
				.append(CONTEXTSTRING);
		String contextName = context.getName();
		if (!contextName.startsWith("/")) {
			name.append("/");
		}
		name.append(contextName);
		return name.toString();
	}

	@Override
	protected void startInternal() throws LifecycleException {
		if (context.getResources() == null) {
			setState(LifecycleState.STARTING);
			return;
		}

		classLoader = new AppBaseWebappClassLoader(context);
		classLoader.start();

		String contextName = context.getName();
		if (!contextName.startsWith("/")) {
			contextName = "/" + contextName;
		}

		try {
			ObjectName cloname = new ObjectName(context.getDomain() + ":type=" + classLoader.getClass().getSimpleName()
					+ HOSTSTRING + context.getParent().getName() + CONTEXTSTRING + contextName);
			Registry.getRegistry(null, null).registerComponent(classLoader, cloname, null);
		} catch (Throwable e) {
			e = ExceptionUtils.unwrapInvocationTargetException(e);
			ExceptionUtils.handleThrowable(e);
			throw new LifecycleException("start: ", e);
		}

		setState(LifecycleState.STARTING);
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		setState(LifecycleState.STOPPING);

		// Remove context attributes as appropriate
		ServletContext servletContext = context.getServletContext();
		servletContext.removeAttribute(Globals.CLASS_PATH_ATTR);

		if (classLoader != null) {
			try {
				classLoader.stop();
			} finally {
				classLoader.destroy();
			}

			// classLoader must be non-null to have been registered
			try {
				String contextName = context.getName();
				if (!contextName.startsWith("/")) {
					contextName = "/" + contextName;
				}
				ObjectName cloname = new ObjectName(
						context.getDomain() + ":type=" + classLoader.getClass().getSimpleName() + HOSTSTRING
								+ context.getParent().getName() + CONTEXTSTRING + contextName);
				Registry.getRegistry(null, null).unregisterComponent(cloname);
			} catch (Exception e) {
				// Do nothing
			}
		}

		classLoader = null;
	}
}
