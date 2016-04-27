package org.jboss.gravel.common.ui;

import org.jboss.gravel.common.annotation.TldAttribute;

/**
 *
 */
public interface HasHtmlAccessAttributes extends HasHtmlEventsAttributes {
    @TldAttribute(
        description = "This attribute assigns an access key to an element. An access key is a single character " +
            "from the document character set."
    )
    String getAccesskey();
}
