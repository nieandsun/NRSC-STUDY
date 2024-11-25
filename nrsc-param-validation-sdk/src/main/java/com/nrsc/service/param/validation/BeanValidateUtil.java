package com.nrsc.service.param.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-04-18
 * Description:  参数校验工具类
 */
public class BeanValidateUtil {

    private BeanValidateUtil() {
    }

    private static final Validator VALIDATOR;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    /***
     * java bean 数据校验
     * 参数符合要求,返回 null,否则返回错误原因(包含参数名)
     *
     * @param target 目标 bean
     */
    public static String validateWithParam(Object target, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(target, groups);
        for (ConstraintViolation<Object> error : constraintViolations) {
            return "[" + error.getPropertyPath().toString() + "]" + error.getMessage();
        }
        return null;
    }
}
