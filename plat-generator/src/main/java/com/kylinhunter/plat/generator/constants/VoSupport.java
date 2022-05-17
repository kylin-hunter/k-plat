package com.kylinhunter.plat.generator.constants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VoSupport {
    VoType[] value() default {VoType.CREATE, VoType.UPDATE};
}
