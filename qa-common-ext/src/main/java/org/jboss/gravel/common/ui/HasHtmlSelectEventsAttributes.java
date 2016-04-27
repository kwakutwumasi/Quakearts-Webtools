package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasHtmlSelectEventsAttributes extends HasHtmlChangeFocusEventsAttributes {
    @TldAttribute (
        description = "Render the HTML onselect attribute.  The onselect event occurs when a user selects some " +
            "text in a text field.",
        deferredType = String.class
    )
    String getOnselect();
}
