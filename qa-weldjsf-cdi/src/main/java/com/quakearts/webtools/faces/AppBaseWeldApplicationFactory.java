package com.quakearts.webtools.faces;

import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;

public class AppBaseWeldApplicationFactory extends ApplicationFactory {
    private final ApplicationFactory applicationFactory;

    private volatile Application application;

    public AppBaseWeldApplicationFactory(ApplicationFactory applicationFactory) {
        this.applicationFactory = applicationFactory;
    }

    protected ApplicationFactory delegate() {
        return applicationFactory;
    }

    @Override
    public Application getApplication() {
        if (application == null) {
            synchronized (this) {
                if (application == null) {
                    application = new AppBaseWeldApplication(delegate().getApplication());
                }
            }
        }
        return application;
    }

    @Override
    public void setApplication(Application application) {
        synchronized (this) {
            this.application = null;
            delegate().setApplication(application);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return delegate().equals(obj);
    }

    @Override
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override
    public String toString() {
        return delegate().toString();
    }

    @Override
    public ApplicationFactory getWrapped() {
        return delegate();
    }
}
