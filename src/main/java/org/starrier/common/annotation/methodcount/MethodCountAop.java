package org.starrier.common.annotation.methodcount;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import parquet.org.slf4j.Logger;
import parquet.org.slf4j.LoggerFactory;

/**
 * @author starrier
 * @date 2021/1/4
 */
@Aspect
@Component
public class MethodCountAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodCountAop.class);

    @Pointcut("@annotation(org.starrier.common.annotation.methodcount.MethodCount)")
    public void methodCount() {

    }

    @Before("methodCount()")
    public void before(JoinPoint joinPoint) {
        Long startTime = System.currentTimeMillis();

        Signature signature = joinPoint.getSignature();

        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();

        LOGGER.info("current method count class name is :[{}] and method is [{}]", className, methodName);
    }

    @After("methodCount()")
    public void after(ProceedingJoinPoint proceedingJoinPoint) {

    }

}
