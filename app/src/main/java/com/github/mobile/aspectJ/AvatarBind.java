package com.github.mobile.aspectJ;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation definition
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface AvatarBind {}
