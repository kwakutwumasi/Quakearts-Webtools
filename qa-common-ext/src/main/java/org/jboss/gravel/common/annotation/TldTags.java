package org.jboss.gravel.common.annotation;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import java.lang.annotation.Target;

/**
 *
 */
@Target(TYPE)
@Retention(SOURCE)
public @interface TldTags {
    TldTag[] value();
}
