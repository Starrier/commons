package org.starrier.common.constant;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Starrier
 * @date 2019/08/25
 */
public class ExceptionNullHandler {

    @Pointcut("@annotation(org.starrier.common.annotation.logger.ExceptionNull)")
    public void log() {
    }

    @Before("log()")
    public Object doBefore(JoinPoint joinPoint) {
        return null;
    }
}
