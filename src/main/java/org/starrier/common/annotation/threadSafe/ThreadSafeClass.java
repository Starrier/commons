package org.starrier.common.annotation.threadSafe;

import net.jcip.annotations.ThreadSafe;

import java.lang.annotation.*;

/**
 * @author Starrier
 * @date 2018/12/15.
 */
@Target({ ElementType.TYPE,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
         })
@ThreadSafe
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafeClass {
}
