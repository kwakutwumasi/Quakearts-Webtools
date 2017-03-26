package org.jboss.gravel.common.annotation;

import static java.lang.annotation.ElementType.PACKAGE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import java.lang.annotation.Target;

/**
 *
 */
@Target(PACKAGE)
@Retention(SOURCE)
public @interface TldTagLib {
    String tlibVersion() default "1.2";

    String jspVersion() default "1.2";

    String fileName();

    String shortName();

    /**
     * Defaults to the javadoc text.
     *
     * @return the description
     */
    String description() default "";

    String uri();
}
