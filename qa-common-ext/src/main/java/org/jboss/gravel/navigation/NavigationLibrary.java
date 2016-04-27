package org.jboss.gravel.navigation;

import org.jboss.gravel.navigation.handler.NavHandler;

import com.sun.faces.facelets.tag.AbstractTagLibrary;

/**
 *
 */
public final class NavigationLibrary extends AbstractTagLibrary {
    public NavigationLibrary() {
        super("http://gravel.jboss.org/jsf/1.0/navigation");

        addTagHandler("nav", NavHandler.class);
    }
}
