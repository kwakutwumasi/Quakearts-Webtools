package org.jboss.gravel.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 *
 */
@Target(METHOD)
@Retention(RUNTIME)
@Inherited
public @interface TldAttribute {
    String name() default "";

    /**
     * Attribute description.
     *
     * @return the description
     */
    String description() default "";

    boolean required() default false;

    boolean rtexprvalue() default false;

    /**
     * Override and prevent this attribute from being
     * added to the TLD.
     *
     * @return {@code true} to remove this attribute from the TLD
     */
    boolean forbid() default false;

    Class<?> deferredType() default void.class;

    Class<?> type() default void.class;
}
