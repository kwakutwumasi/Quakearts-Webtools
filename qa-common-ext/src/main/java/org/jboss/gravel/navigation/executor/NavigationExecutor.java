package org.jboss.gravel.navigation.executor;

import java.io.Serializable;

import javax.faces.context.FacesContext;

/**
 *
 */
public interface NavigationExecutor extends Serializable {
    void execute(FacesContext context);
}
