package org.starrier.common.annotation.threadsafe;

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
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafeMethod {
}
