package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasHtmlChangeFocusEventsAttributes extends HasHtmlFocusEventsAttributes {
    @TldAttribute (
        description = "Render the HTML onchange attribute.  The onchange event occurs when a control loses the input " +
            "focus <em>and</em> its value has been modified since gaining focus.",
        deferredType = String.class
    )
    String getOnchange();
}
