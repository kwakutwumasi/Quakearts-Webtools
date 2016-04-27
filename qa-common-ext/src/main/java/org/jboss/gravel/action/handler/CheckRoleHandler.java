package org.jboss.gravel.action.handler;

import java.io.IOException;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.util.DelimitedStringList;
import org.jboss.gravel.common.util.MessageUtils;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagHandler;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 */
@TldTag (
    name = "checkRole",
    description = "Check to see that the current logged in user is authorized to certain role(s) (or not) before " +
        "evaluating the nested tags.",
    attributes = {
        @TldAttribute (
            name = "roles",
            description = "A role or a comma-separated list of roles to check for.  The user must be in at least one " +
                "of the given roles in order for evaluation to succeed.",
            required = true
        ),
        @TldAttribute (
            name = "message",
            description = "If given, the supplied message will be added if the user is not authorized."
        ),
        @TldAttribute (
            name = "severity",
            description = "The severity of the message.  If not provided, default to error.  The value must be one of " +
                "the following: " +
                "<ul>" +
                "<li><code>info</code></li>" +
                "<li><code>warn</code></li>" +
                "<li><code>error</code></li>" +
                "<li><code>fatal</code></li>" +
                "</ul>"
        ),
        @TldAttribute (
            name = "authorized",
            description = "A boolean value that specifies whether the user must be authorized " +
                "or not authorized to match the content.  Defaults to <code>true</code>.",
            deferredType = boolean.class
        )
    }
)
public final class CheckRoleHandler extends TagHandler {
    private final TagAttribute rolesAttribute;
    private final TagAttribute messageAttribute;
    private final TagAttribute severityAttribute;
    private final TagAttribute authorizedAttribute;

    public CheckRoleHandler(final TagConfig config) {
        super(config);
        rolesAttribute = getRequiredAttribute("roles");
        messageAttribute = getAttribute("message");
        severityAttribute = getAttribute("severity");
        authorizedAttribute = getAttribute("authorized");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            final boolean authorized;
            if (authorizedAttribute != null) {
                final ValueExpression valueExpression = authorizedAttribute.getValueExpression(ctx, Boolean.class);
                if (valueExpression != null) {
                    final Object value = valueExpression.getValue(ctx);
                    if (value instanceof Boolean) {
                        authorized = ((Boolean)value).booleanValue();
                    } else if (value != null) {
                        authorized = Boolean.valueOf(value.toString());
                    } else {
                        authorized = false;
                    }
                } else {
                    authorized = true;
                }
            } else {
                authorized = true;
            }
            final ValueExpression rolesExpression = rolesAttribute.getValueExpression(ctx, String.class);
            final Object rolesValue = rolesExpression.getValue(ctx);
            if (rolesValue != null && ! "".equals(rolesValue)) {
                final FacesContext facesContext = ctx.getFacesContext();
                final ExternalContext externalContext = facesContext.getExternalContext();
                for (String role : new DelimitedStringList(rolesValue.toString())) {
                    if (externalContext.isUserInRole(role)) {
                        if (authorized) {
                            nextHandler.apply(ctx, parent);
                        }
                        return;
                    }
                }
                if (messageAttribute != null) {
                    final FacesMessage.Severity severity;
                    if (severityAttribute != null) {
                        final String value = severityAttribute.getValue(ctx);
                        severity = MessageUtils.getSeverityForName(value.toLowerCase().trim());
                        if (severity == null) {
                            throw new TagException(tag, "Invalid value for severity attribute");
                        }
                    } else {
                        severity = FacesMessage.SEVERITY_ERROR;
                    }
                    final String message = messageAttribute.getValue(ctx);
                    if (message != null) {
                        facesContext.addMessage(null, new FacesMessage(severity, message, null));
                    }
                }
                if (! authorized) {
                    nextHandler.apply(ctx, parent);
                }
            } else {
                if (authorized) {
                    nextHandler.apply(ctx, parent);
                }
            }
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }
}
