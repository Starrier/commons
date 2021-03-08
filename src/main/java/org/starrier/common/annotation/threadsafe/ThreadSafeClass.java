package org.starrier.common.annotation.threadsafe;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Starrier
 * @date 2018/12/15.
 */
@Target({ElementType.TYPE,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafeClass {
}
