package org.jboss.gravel.action.handler;

import java.io.IOException;

import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagHandler;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.component.UIComponent;
import javax.faces.component.ActionSource;
import javax.faces.FacesException;
import javax.el.ELException;

import org.jboss.gravel.action.action.LogOutActionListener;
import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;

/**
 *
 */
@TldTag (
    name = "logOut",
    description = "Add an action handler to the enclosing component that invalidates the current session.",
    attributes= {
            @TldAttribute (
                    name = "url",
                    description = "Redirect url after logout",
                    required = true,
                    deferredType = String.class
            )
    }
)
public final class LogOutHandler extends TagHandler {
    public LogOutHandler(final TagConfig config) {
        super(config);
    }

	private TagAttribute urlAttribute = getRequiredAttribute("url");
    
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (ComponentHandler.isNew(parent)) {
                if (! (parent instanceof ActionSource)) {
                    throw new TagException(tag, "Parent component is not an ActionSource");
                }
                ((ActionSource)parent).addActionListener(new LogOutActionListener(urlAttribute.getValueExpression(ctx, String.class)));
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
