package org.jboss.gravel.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;

/**
 *
 */
public final class MessageUtils {
    private static final Map<String, FacesMessage.Severity> severityMap;
    private static final Set<FacesMessage.Severity> allSeverities;

    private MessageUtils() {
        // nothing
    }

    static {
        final Map<String, FacesMessage.Severity> severityTempMap = new HashMap<String, FacesMessage.Severity>();
        severityTempMap.put("fatal", FacesMessage.SEVERITY_FATAL);
        severityTempMap.put("error", FacesMessage.SEVERITY_ERROR);
        severityTempMap.put("warn", FacesMessage.SEVERITY_WARN);
        severityTempMap.put("info", FacesMessage.SEVERITY_INFO);
        severityMap = Collections.unmodifiableMap(severityTempMap);
        final Set<FacesMessage.Severity> allSeveritiesTempSet = new LinkedHashSet<FacesMessage.Severity>();
        allSeveritiesTempSet.addAll(severityTempMap.values());
        allSeverities = Collections.unmodifiableSet(allSeveritiesTempSet);
    }

    public static final Set<FacesMessage.Severity> getAllSeverities() {
        return allSeverities;
    }

    public static final Set<String> getAllSeverityStrings() {
        return severityMap.keySet();
    }

    public static FacesMessage.Severity getSeverityForName(String name) {
        return severityMap.get(name);
    }
}
