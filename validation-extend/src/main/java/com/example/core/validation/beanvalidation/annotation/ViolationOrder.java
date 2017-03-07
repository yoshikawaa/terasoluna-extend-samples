package com.example.core.validation.beanvalidation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * annotation that determine order of bean validation violations.
 */
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.TYPE_PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ViolationOrder {
    /**
     * @return alias　for {@link #order()}
     */
    @AliasFor("order")
    int value() default Integer.MAX_VALUE;

    /**
     * @return order of property paths.
     */
    @AliasFor("value")
    int order() default Integer.MAX_VALUE;
}
