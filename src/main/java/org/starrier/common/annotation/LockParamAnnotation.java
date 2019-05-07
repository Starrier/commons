package org.starrier.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Starrier
 * @date 2018/12/21.
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.TYPE})
public @interface LockParamAnnotation {

    /**
     * 参数的域，用于表明参数的业务场景，例如 orderSn.
     * @return String.
     * */
    String value() default "";
}
