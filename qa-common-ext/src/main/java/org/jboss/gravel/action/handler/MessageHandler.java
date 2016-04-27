package org.jboss.gravel.action.handler;

import java.io.IOException;
import java.util.List;

import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.TagHandler;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.component.UIComponent;
import javax.faces.component.ActionSource;
import javax.faces.FacesException;
import javax.el.ELException;

import org.jboss.gravel.action.action.MessageActionListener;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
@TldTag (
    name = "message",
    description = "Read all matching messages from the current FacesContext.  Store the result as a list into the " +
        "target EL expression.",
    attributes = {
        @TldAttribute (
            name = "clientId",
            description = "The client ID to restrict the message list to.  If not given, all messages are returned.  If " +
                "set to the empty string, only global messages will be returned."
        ),
        @TldAttribute (
            name = "target",
            description = "The EL expression into which the result list is stored.",
            required = true,
            deferredType = List.class
        ),
        @TldAttribute (
            name = "severity",
            description = "A comma-separated list of severity values.  Only messages of the given severity will be " +
                "returned.  Each value must be one of the following: " +
                "<ul>" +
                "<li><code>info</code></li>" +
                "<li><code>warn</code></li>" +
                "<li><code>error</code></li>" +
                "<li><code>fatal</code></li>" +
                "</ul>"
        )
    }
)
public final class MessageHandler extends TagHandler {

    private final TagAttribute clientIdAttribute;
    private final TagAttribute targetAttribute;
    private final TagAttribute severityAttribute;

    public MessageHandler(final TagConfig config) {
        super(config);
        clientIdAttribute = getAttribute("clientId");
        targetAttribute = getRequiredAttribute("target");
        severityAttribute = getAttribute("severity");
    }

    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (ComponentHandler.isNew(parent)) {
                if (! (parent instanceof ActionSource)) {
                    throw new TagException(tag, "Parent component is not an ActionSource");
                }
                ((ActionSource) parent).addActionListener(
                    new MessageActionListener(
                        clientIdAttribute == null ? null : clientIdAttribute.getValueExpression(ctx, String.class),
                        targetAttribute.getValueExpression(ctx, List.class),
                        severityAttribute == null ? null : severityAttribute.getValueExpression(ctx, String.class)
                    )
                );
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
