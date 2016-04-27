package org.jboss.gravel.navigation;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

/**
 *
 */
public interface NavigationState extends Serializable {
    String NAV_KEY = "org.jboss.gravel.navigation.STATE";

    ActionEvent getActionEvent();
}
