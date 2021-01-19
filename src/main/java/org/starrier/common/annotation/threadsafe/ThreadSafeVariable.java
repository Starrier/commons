package org.starrier.common.annotation.threadsafe;

import net.jcip.annotations.ThreadSafe;

<<<<<<< HEAD
import java.lang.annotation.*;
=======
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
>>>>>>> master

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
public @interface ThreadSafeVariable {
}
