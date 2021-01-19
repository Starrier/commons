package org.starrier.common.annotation.loginlimt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author starrier
 * @date 2020/12/31
 */
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface LoginLimit {

    /**
     * 唯一标识符，用于登录校验
     */
    String identifier();

    /**
     * 在多长时间内监控, 如希望在 60s 内尝试
     * 次数限制为5次, 那么 watch=60; unit: s
     */
    long watch() default 60;

    /**
     * 锁定时长, unit: s
     */
    long lock() default 60;

    /**
     * 错误的尝试次数
     */
    int times() default 5;

}
