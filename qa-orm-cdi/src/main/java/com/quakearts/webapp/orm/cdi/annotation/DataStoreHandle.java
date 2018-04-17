package com.quakearts.webapp.orm.cdi.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
public @interface DataStoreHandle {
}
