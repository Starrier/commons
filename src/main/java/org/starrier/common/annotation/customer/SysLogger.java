package org.starrier.common.annotation.customer;

import java.lang.annotation.*;

/**
 * @author Starrier
 * @date 2019/08/25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}
