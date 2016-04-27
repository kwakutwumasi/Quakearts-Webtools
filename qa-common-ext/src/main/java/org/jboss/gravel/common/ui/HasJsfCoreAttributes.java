package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;
import org.jboss.gravel.common.annotation.TldAttributes;

import javax.faces.component.UIComponent;

/**
 *
 */
@TldAttributes({
        @TldAttribute(
                name = "binding",
                description = "A writable ValueExpression to store this component instance into on request.",
                deferredType = UIComponent.class
        )
})
public interface HasJsfCoreAttributes {
    @TldAttribute(
            description = "The ID of this component.",
            type = String.class
    )
    String getId();

    @TldAttribute(
            description = "A flag indicating whether this component should be rendered."
    )
    boolean isRendered();
}
