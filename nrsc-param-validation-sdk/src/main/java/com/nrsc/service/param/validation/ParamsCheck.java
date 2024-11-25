package com.nrsc.service.param.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-04-18
 * Description: 参数校验注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsCheck {
}
