package com.quakearts.security.cryptography.cdi;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

@Retention(RUNTIME)
@Qualifier
public @interface CryptoServiceHandle {
}
