package org.starrier.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义 手机格式 校验注解.
 *
 * @author Starrier
 * @date 2018/11/17.
 */
@Target({ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {
    /**
     * 默认 为空.
     * 校验不通过，输出 message.
     *
     * @return the {@code result} with {@link Boolean} type.
     */
    boolean required() default false;

    /**
     * Define the message which will return when the validation is false.
     *
     * @return the result with {@code message}.
     */
    String message() default "手机号码校验格式不通过";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
