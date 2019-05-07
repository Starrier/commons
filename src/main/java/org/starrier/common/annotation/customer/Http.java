package org.starrier.common.annotation.customer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Starrier
 * @date 2019/05/07
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Http {

}
