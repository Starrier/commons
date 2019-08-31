package org.starrier.common.annotation.customer;

import java.lang.annotation.*;

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
