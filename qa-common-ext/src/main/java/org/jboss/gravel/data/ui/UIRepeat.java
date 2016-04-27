package org.jboss.gravel.data.ui;

import org.jboss.gravel.common.annotation.TldTag;
import org.jboss.gravel.common.ui.HasJsfCoreAttributes;
import org.jboss.gravel.common.ui.UICollection;

/**
 *
 */
@TldTag(
        name = "repeat",
        description = "Repeat the content a given number of times."
)
public final class UIRepeat extends UICollection implements HasJsfCoreAttributes {

    public static final String COMPONENT_TYPE = "gravel.Repeat";
    public static final String RENDERER_TYPE = null;
    public static final String COMPONENT_FAMILY = "gravel.data";

    private static final long serialVersionUID = 1L;

    public UIRepeat() {
        setRendererType(RENDERER_TYPE);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }
}
