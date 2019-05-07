package org.starrier.common.annotation;

import net.jcip.annotations.ThreadSafe;

import java.lang.annotation.*;

/**
 * @author Starrier
 * @date 2018/12/15.
 */
@Target({ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
@ThreadSafe
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafeMethod {
}
