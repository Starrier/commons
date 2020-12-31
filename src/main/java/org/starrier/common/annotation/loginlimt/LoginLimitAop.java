package org.starrier.common.annotation.loginlimt;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.starrier.common.result.Result;
import parquet.org.slf4j.Logger;
import parquet.org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author starrier
 * @date 2020/12/31
 */
@Aspect
@Component
public class LoginLimitAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginLimitAop.class);

    private final StringRedisTemplate stringRedisTemplate;

    public LoginLimitAop(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Around("@annotation(LoginLimit)")
    public Object handleLimit(ProceedingJoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        // TODO 直接将参数注入
        final LoginLimit loginLimit = method.getAnnotation(LoginLimit.class);

        //
        final String identifier = loginLimit.identifier();
        final long watch = loginLimit.watch();
        final int times = loginLimit.times();
        final long lock = loginLimit.lock();

        String identifierValue = null;
        try {
            final Object arg = joinPoint.getArgs()[0];
            final Field declaredField = arg.getClass().getDeclaredField(identifier);
            declaredField.setAccessible(true);
            identifierValue = (String) declaredField.get(arg);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error(">>> invalid identifier [{}], cannot find this field in request params", identifier);
        }
        if (StringUtils.isBlank(identifierValue)) {
            LOGGER.error(">>> the value of RedisLimit.identifier cannot be blank, invalid identifier: {}", identifier);
            return Result.error("login error");
        }

        // check User locked
        final ValueOperations<String, String> ssOps = stringRedisTemplate.opsForValue();
        final String flag = ssOps.get(identifierValue);
        if (flag != null && "lock".contentEquals(flag)) {

            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable e) {
            return handleLoginException(e, identifierValue, watch, times, lock);
        }
        return Result.success(proceed, "success");

    }

    private Result handleLoginException(Throwable e, String identifierValue, final long watch, final int times, final long lock) {

        if (e instanceof LoginException) {
            LOGGER.info(">>> handle login exception...");
            final ValueOperations<String, String> ssOps = stringRedisTemplate.opsForValue();
            Boolean exist = stringRedisTemplate.hasKey(identifierValue);
            if (exist == null || !exist) {
                ssOps.set(identifierValue, "1", watch, TimeUnit.SECONDS);
                return Result.success();
            }

            String count = ssOps.get(identifierValue);
            if (StringUtils.isBlank(count)) {
                return Result.error("not found");
            }
            // has been reached the limitation
            if (Integer.parseInt(count) + 1 == times) {
                LOGGER.info(">>> [{}] has been reached the limitation and will be locked for {}s", identifierValue, lock);
                ssOps.set(identifierValue, "lock", lock, TimeUnit.SECONDS);
                return Result.success();
            }
            ssOps.increment(identifierValue);
        }
        LOGGER.error(">>> RedisLimitAOP cannot handle {}", e.getClass().getName());
        return Result.success();
    }
}
