package org.starrier.common.annotation.customer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe
 *
 *
 *
 * @author Starrier
 * @date 2018/12/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DuplicateSubmitToken {

    /**
     * Save duplicate commit tags, default to true
     * */
    boolean save() default true;

}
