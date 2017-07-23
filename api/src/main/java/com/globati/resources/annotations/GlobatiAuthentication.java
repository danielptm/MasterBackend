package com.globati.resources.annotations;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by daniel on 12/25/16.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface GlobatiAuthentication {}
