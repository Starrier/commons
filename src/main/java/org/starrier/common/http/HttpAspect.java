package org.starrier.common.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.starrier.common.constant.HttpConstant.ARGS;
import static org.starrier.common.constant.HttpConstant.CLASS_METHOD;
import static org.starrier.common.constant.HttpConstant.CLASS_NAME;
import static org.starrier.common.constant.HttpConstant.IP;
import static org.starrier.common.constant.HttpConstant.METHOD;
import static org.starrier.common.constant.HttpConstant.URL;

/**
 * @author Starrier
 * @date 2018/11/6.
 */
@Aspect
@Slf4j
@Component
public class HttpAspect {

    @Pointcut("@annotation(org.starrier.common.annotation.Http)")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (null != attributes) {
            request = attributes.getRequest();
        }
        Map<String, java.io.Serializable> params = new HashMap<>();
        if (null != request) {
            params.put(URL, request.getRequestURL());
        }
        if (null != request) {
            params.put(METHOD, request.getMethod());
        }
        if (null != request) {
            params.put(IP, request.getRemoteAddr());
        }
        params.put(CLASS_NAME, joinPoint.getSignature().getDeclaringTypeName());
        params.put(CLASS_METHOD, joinPoint.getSignature().getName());
        params.put(ARGS, joinPoint.getArgs());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        log.info("REQUEST:{}", gson.toJson(params));
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint point) {
        try {
            Object object = point.proceed();
            log.info("doAround");
            return object;
        } catch (Throwable throwable) {
            log.info("doAround.......");
            return null;
        }
    }

    @After("log()")
    public void doAfter() {
        log.info("doAfter ......");
    }

    /**
     * <p>Fetch</p>
     *
     * @param object
     * @return return value
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("RESPONSE : {}", object.toString());
    }

    @AfterThrowing(pointcut = "log()")
    public void doAfterThrowing() {
        log.error("doAfterThrowing: {}", " 异常情况!");
    }
}
