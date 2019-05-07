package org.starrier.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Starrier
 * @date 2018/12/21.
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface LockAnnotation {

    /**
     * 加锁的 key 的域，用于前缀识别.
     * @return String.
     * */
    String lockField() default "";

    /**
     * 锁自定释放的时间.
     *
     * @return int.
     */
    int lockTime() default 3;
}
