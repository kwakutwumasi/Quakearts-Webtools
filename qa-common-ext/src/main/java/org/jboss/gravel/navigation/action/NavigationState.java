package org.jboss.gravel.navigation.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.jboss.gravel.navigation.executor.NavigationExecutor;

public class NavigationState implements Serializable {
    private static final long serialVersionUID = 1L;
    static final Logger log = Logger.getLogger("org.jboss.gravel.navigation.action.NavigationState");
    public static final String NAV_KEY = "org.jboss.gravel.navigation.NAV_KEY";
    
    private final Map<String,NavigationExecutor> navMap = new HashMap<String,NavigationExecutor>();

    public void addRule(String name, NavigationExecutor executor) {
        if (name == null) {
            return;
        }
        if (navMap.containsKey(name)) {
            log.warning("Navigation map already contains a rule for outcome '" + name + "'; it will be overwritten");
        }
        navMap.put(name, executor);
    }

    public boolean hasRule(String name) {
        return navMap.containsKey(name);
    }

    public NavigationExecutor getRule(String name) {
        return navMap.get(name);
    }

}