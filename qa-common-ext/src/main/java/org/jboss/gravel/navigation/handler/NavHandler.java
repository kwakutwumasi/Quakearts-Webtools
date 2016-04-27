package org.jboss.gravel.navigation.handler;

import java.io.IOException;

import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.navigation.action.NavActionListener;
import org.jboss.gravel.navigation.action.NavigationState;
import org.jboss.gravel.navigation.executor.NavigationExecutor;
import org.jboss.gravel.navigation.executor.StayExecutorImpl;
import org.jboss.gravel.navigation.executor.UrlRedirectExecutorImpl;
import org.jboss.gravel.navigation.executor.ViewExecutorImpl;
import org.jboss.gravel.navigation.executor.ViewRedirectExecutorImpl;
import org.jboss.gravel.navigation.executor.StayRedirectExecutorImpl;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagException;
import javax.faces.view.facelets.TagHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
@TldTag(
        name = "nav",
        description = "Specify the navigation target for an action outcome."
)
public final class NavHandler extends TagHandler {

    private static enum OutcomeType {
        STAY,
        VIEW,
        REDIRECT
    }

    private static enum TargetType {
        IMPLIED,
        VIEW,
        URL
    }

    private final OutcomeType outcomeType;
    private final TargetType targetType;

    private final TagAttribute outcomeNameAttr;
    private final TagAttribute viewIdAttr;
    private final TagAttribute urlAttr;
    private final TagAttribute redirectAttr;
    private final TagAttribute storeMessagesAttr;

    public NavHandler(final TagConfig config) {
        super(config);
        outcomeNameAttr = getRequiredAttribute("outcome");
        viewIdAttr = getAttribute("viewId");
        if (viewIdAttr != null) {
            forbidAttribute("url");
        }
        urlAttr = getAttribute("url");
        if (urlAttr != null) {
            forbidAttribute("viewId");
            forbidAttribute("redirect");
        }
        redirectAttr = getAttribute("redirect");
        final String redirectString = redirectAttr == null ? null : redirectAttr.getValue();
        final Boolean redirect = redirectString == null ? urlAttr != null : Boolean.valueOf(redirectString);
        if (redirect) {
            outcomeType = OutcomeType.REDIRECT;
            targetType = urlAttr != null ? TargetType.URL : viewIdAttr != null ? TargetType.VIEW : TargetType.IMPLIED;
            storeMessagesAttr = getAttribute("storeMessages");
        } else {
            forbidAttribute("url");
            forbidAttribute("storeMessages");
            storeMessagesAttr = null;
            targetType = viewIdAttr != null ? TargetType.VIEW : TargetType.IMPLIED;
            outcomeType = viewIdAttr != null ? OutcomeType.VIEW : OutcomeType.STAY;
        }
    }

    protected void forbidAttribute(String name) throws TagException {
        if (getAttribute(name) != null) {
            throw new TagException(tag, "Attribute '" + name + "' not allowed here");
        }
    }

    @TldAttribute(
            description = "A comma-separated list of names of outcomes to assign this navigation action to.",
            name = "outcome",
            type = String.class,
            required = true
    )
    public TagAttribute getOutcomeNameAttr() {
        return outcomeNameAttr;
    }

    @TldAttribute(
            description = "Get the target view ID.  This attribute may not coexist with the <code>url</code> attribute. " +
                    "If neither the <code>viewId</code> or <code>url</code> attributes are given, the outcome will result " +
                    "in staying on the current view.",
            name = "viewId",
            deferredType = String.class
    )
    public TagAttribute getViewIdAttr() {
        return viewIdAttr;
    }

    @TldAttribute(
            description = "Get the target URL.  This attribute may not coexist with the <code>viewId</code> attribute. " +
                    "If neither the <code>viewId</code> or <code>url</code> attributes are given, the outcome will result " +
                    "in staying on the current view.",
            name = "url",
            deferredType = String.class
    )
    public TagAttribute getUrlAttr() {
        return urlAttr;
    }

    @TldAttribute(
            description = "A boolean that specifies whether to redirect to the target.  If a <code>url</code> is given, " +
                    "this attribute may not be specified (since it is implied that this attribute must be true).  This " +
                    "attribute defaults to <code>false</code>.",
            name = "redirect",
            type = boolean.class
    )
    public TagAttribute getRedirectAttr() {
        return redirectAttr;
    }

    @TldAttribute(
        description = "A boolean that specifies whether to store messages in the session for later retrieval across " +
            "a redirect.",
        name = "storeMessages",
        deferredType = boolean.class
    )
    public TagAttribute getStoreMessagesAttr() {
        return storeMessagesAttr;
    }

	public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, ELException {
        try {
            if (!(parent instanceof ActionSource)) {
                throw new TagException(tag, "Parent is not an ActionSource, it is: " + parent);
            }

            if (!ComponentHandler.isNew(parent)) {
                // only add once
                  return;
            }

            final String outcomeNames = outcomeNameAttr.getValue(ctx);

            NavigationState state = (NavigationState) parent.getAttributes().get(NavigationState.NAV_KEY);
            if(state==null){
            	state = new NavigationState();
            	parent.getAttributes().put(NavigationState.NAV_KEY, state);
            }
            
            ((ActionSource)parent).addActionListener(new NavActionListener(state));
            
            final int len = outcomeNames.length();
            for (int i = 0; i < len; ) {
                final int commaIndex = outcomeNames.indexOf(',', i);
                final String name;
                if (commaIndex == -1) {
                    name = outcomeNames.substring(i).trim();
                    i = len;
                } else {
                    name = outcomeNames.substring(i, commaIndex).trim();
                    i = commaIndex + 1;
                }
                if (log.isLoggable(Level.FINE)) {
                    log.log(Level.FINE, "Registering outcome '" + name + "' as type '" + outcomeType + "' on component " + parent);
                }

                final NavigationExecutor executor;

                switch (outcomeType) {
                    case STAY:
                        executor = new StayExecutorImpl();
                        break;
                    case VIEW:
                        // if outcomeType is view, then targetType must be view
                        executor = new ViewExecutorImpl(viewIdAttr.getValueExpression(ctx, String.class));
                        break;
                    case REDIRECT:
                        switch (targetType) {
                            case URL:
                                executor = new UrlRedirectExecutorImpl(urlAttr.getValueExpression(ctx, String.class),
                                    storeMessagesAttr == null ? null : storeMessagesAttr.getValueExpression(ctx, Boolean.class));
                                break;
                            case VIEW:
                                executor = new ViewRedirectExecutorImpl(viewIdAttr.getValueExpression(ctx, String.class),
                                    storeMessagesAttr == null ? null : storeMessagesAttr.getValueExpression(ctx, Boolean.class));
                                break;
                            case IMPLIED:
                                executor = new StayRedirectExecutorImpl(storeMessagesAttr == null ? null : storeMessagesAttr.getValueExpression(ctx, Boolean.class));
                                break;
                            default:
                                throw new IllegalStateException("Illegal value in enum: " + targetType);
                        }
                        break;
                    default:
                        throw new IllegalStateException("Illegal value in enum: " + outcomeType);
                }

                state.addRule(name, executor);
            }

            // Now apply any tags contained in this one...
            nextHandler.apply(ctx, parent);
        } catch (TagException tex) {
            throw tex;
        } catch (RuntimeException rex) {
            TagException tex = new TagException(tag, "An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
            tex.setStackTrace(rex.getStackTrace());
            throw tex;
        }
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.navigation.handler.NavHandler");
}
