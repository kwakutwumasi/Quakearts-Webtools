package org.jboss.gravel.navigation.phase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public final class NavigationPhaseListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings ({"unchecked"})
    public void beforePhase(PhaseEvent phaseEvent) {
        final PhaseId phaseId = phaseEvent.getPhaseId();
        final FacesContext facesContext = phaseEvent.getFacesContext();
        final ExternalContext externalContext = facesContext.getExternalContext();
        if (phaseId == PhaseId.RENDER_RESPONSE) {
            final Object requestObject = externalContext.getRequest();
            if (requestObject != null && (requestObject instanceof HttpServletRequest)) {
                HttpServletRequest request = (HttpServletRequest) requestObject;
                StringBuilder uri = new StringBuilder();
                uri.append(request.getRequestURI());
                final String queryString = request.getQueryString();
                if (queryString != null) {
                    uri.append('?').append(queryString);
                }
                final String uriString = uri.toString();
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Reconstructed URI string: '" + uriString + "'");
                }
                final Map<String,Object> sessionMap = externalContext.getSessionMap();
                final ConcurrentMap<String, Map<String, List<FacesMessage>>> masterMessageMap;
                synchronized (sessionMap) {
                    masterMessageMap = (ConcurrentMap<String, Map<String, List<FacesMessage>>>) sessionMap.get("gravel.Navigation.MESSAGES");
                }
                if (masterMessageMap == null) {
                    return;
                }
                final Map<String, List<FacesMessage>> messageMap = masterMessageMap.remove(uriString);
                if (messageMap == null) {
                    return;
                }
                int c = 0;
                for (String clientId : messageMap.keySet()) {
                    for (FacesMessage message : messageMap.get(clientId)) {
                        c++;
                        facesContext.addMessage(clientId, message);
                    }
                }
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Restored " + c + " message(s) from URI string: '" + uriString + "'");
                }
            }
        }
    }

    @SuppressWarnings ({"unchecked"})
    public void afterPhase(PhaseEvent phaseEvent) {
        final PhaseId phaseId = phaseEvent.getPhaseId();
        final FacesContext facesContext = phaseEvent.getFacesContext();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Map<String, Object> requestMap = externalContext.getRequestMap();
        if (phaseId == PhaseId.RENDER_RESPONSE || facesContext.getResponseComplete()) {
            final String target = (String) requestMap.get("gravel.MessageMapKey");
            if (target != null) {
                final Iterator<String> idIterator = facesContext.getClientIdsWithMessages();
                Map<String, List<FacesMessage>> messageMap = new LinkedHashMap<String, List<FacesMessage>>();
                int c = 0;
                while (idIterator.hasNext()) {
                    String id = idIterator.next();
                    final ArrayList<FacesMessage> list = new ArrayList<FacesMessage>();
                    messageMap.put(id, list);
                    final Iterator<FacesMessage> msgIterator = facesContext.getMessages(id);
                    while (msgIterator.hasNext()) {
                        final FacesMessage message = msgIterator.next();
                        list.add(message);
                        c++;
                    }
                }
                final String messageKey;
                if (target.startsWith("/")) {
                    messageKey = target;
                } else {
                    final Object requestObject = externalContext.getRequest();
                    if (requestObject != null && (requestObject instanceof HttpServletRequest)) {
                        final HttpServletRequest request = (HttpServletRequest) requestObject;
                        final String requestURI = request.getRequestURI();
                        final int i = requestURI.lastIndexOf('/');
                        if (i == -1) {
                            // oh well, we tried
                            messageKey = target;
                        } else {
                            messageKey = requestURI.substring(0, i) + "/" + target;
                        }
                    } else {
                        throw new FacesException("Cannot redirect from non-servlet container");
                    }
                }
                final Map<String,Object> sessionMap = externalContext.getSessionMap();
                final ConcurrentMap<String,Map<String, List<FacesMessage>>> masterMessageMap;
                synchronized (sessionMap) {
                    if (sessionMap.containsKey("gravel.Navigation.MESSAGES")) {
                        masterMessageMap = (ConcurrentMap<String, Map<String, List<FacesMessage>>>) sessionMap.get("gravel.Navigation.MESSAGES");
                    } else {
                        masterMessageMap = new ConcurrentHashMap<String, Map<String, List<FacesMessage>>>();
                        sessionMap.put("gravel.Navigation.MESSAGES", masterMessageMap);
                    }
                }
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Stored " + c + " messages for URI string: '" + messageKey + "'");
                }
                masterMessageMap.put(messageKey, messageMap);
            }
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.navigation.phase.NavigationPhaseListener");
}
