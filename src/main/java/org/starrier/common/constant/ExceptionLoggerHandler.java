package org.starrier.common.constant;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Starrier
 * @date 2019/08/25
 */
public class ExceptionLoggerHandler {

    @Pointcut("@annotation(org.starrier.common.annotation.logger.ExceptionLogger)")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

    }

    @Around("log()")
    public void around() {

    }
}
