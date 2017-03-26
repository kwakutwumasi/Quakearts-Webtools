package org.jboss.gravel.action.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jboss.gravel.common.util.MessageUtils;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 */
public final class MessageActionListener implements ActionListener, Serializable {
    private final ValueExpression clientIdExpression;
    private final ValueExpression targetExpression;
    private final ValueExpression severityExpression;

    private static final long serialVersionUID = 1L;

    public MessageActionListener(final ValueExpression clientIdExpression, final ValueExpression targetExpression, final ValueExpression severityExpression) {
        this.clientIdExpression = clientIdExpression;
        this.targetExpression = targetExpression;
        this.severityExpression = severityExpression;
    }

    public void processAction(ActionEvent event) throws AbortProcessingException {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ELContext elContext = facesContext.getELContext();
        final List<FacesMessage> messageList = new ArrayList<FacesMessage>();
        final Iterator<FacesMessage> messages;
        final Set<FacesMessage.Severity> severities;
        if (severityExpression != null) {
            final Object severityValue = severityExpression.getValue(elContext);
            if (severityValue != null) {
                severities = new HashSet<FacesMessage.Severity>();
                final String severityString = severityValue.toString();
                int idx = 0;
                int commaIdx;
                do {
                    commaIdx = severityString.indexOf(',', idx);
                    final String severity;
                    if (commaIdx == -1) {
                        severity = severityString.substring(idx).trim().toLowerCase();
                    } else {
                        severity = severityString.substring(idx, commaIdx).trim().toLowerCase();
                        idx = commaIdx + 1;
                    }
                    if (severity.length() > 0) {
                        if (MessageUtils.getAllSeverityStrings().contains(severity)) {
                            severities.add(MessageUtils.getSeverityForName(severity));
                        } else {
                            throw new FacesException("Invalid severity specified: '" + severity + "'");
                        }
                    }
                } while (commaIdx != -1);
            } else {
                severities = MessageUtils.getAllSeverities();
            }
        } else {
            severities = MessageUtils.getAllSeverities();
        }
        if (clientIdExpression != null) {
            final Object clientIdValue = clientIdExpression.getValue(elContext);
            if (clientIdValue != null) {
                final String clientIdPath = clientIdValue.toString();
                if (clientIdPath != null && clientIdPath.length() > 0) {
                    final UIComponent component = event.getComponent().findComponent(clientIdPath);
                    if (component != null) {
                        final String actualClientId = component.getClientId(facesContext);
                        if (actualClientId != null) {
                            messages = facesContext.getMessages(clientIdPath);
                        } else {
                            messages = Collections.<FacesMessage>emptyList().iterator();
                        }
                    } else {
                        messages = Collections.<FacesMessage>emptyList().iterator();
                    }
                } else {
                    messages = facesContext.getMessages("");
                }
            } else {
                messages = facesContext.getMessages();
            }
        } else {
            messages = facesContext.getMessages();
        }
        while (messages.hasNext()) {
            final FacesMessage message = messages.next();
            if (severities.contains(message.getSeverity())) {
                messageList.add(message);
            }
        }
        targetExpression.setValue(elContext, messageList);
    }
}
