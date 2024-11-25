package com.nrsc.service.param.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;

import org.junit.Test;

import lombok.Data;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-04-19
 * Description:
 */
public class BeanValidateUtilTest {

    @Data
    public static class Phone {
        @NotBlank(message = "[基础校验]color不能为空")
        private String color;
    }

    @Data
    public static class Woman {
        @NotBlank(message = "[分组校验]name不能为空", groups = {Insert.class})
        private String name;

        @AgeCheck(message = "[自定义校验]未满18周岁")
        private Integer age;
    }

    public interface Insert {
    }

    public interface Update {
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(
            // 添加效验器
            validatedBy = {AgeConstraintValidator.class}
    )
    public @interface AgeCheck {

        String message() default "{error}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }


    public static class AgeConstraintValidator implements ConstraintValidator<AgeCheck, Integer> {

        public AgeConstraintValidator() {
        }

        @Override
        public boolean isValid(Integer age, ConstraintValidatorContext context) {
            if (age == null) {
                return false;
            }
            return age > 18;
        }

    }

    @Test
    public void validateWithParam001() {

        //基础校验
        Phone phone = new Phone();
        String res1 = BeanValidateUtil.validateWithParam(phone);
        System.out.println(res1);

        System.out.println("===1,2====");

        //分组校验
        Woman woman = new Woman();
        String res2 = BeanValidateUtil.validateWithParam(woman, Insert.class);
        System.out.println(res2);

        System.out.println("===2,3====");

        //自定义校验
        woman.name = "yoyo";
        String res3 = BeanValidateUtil.validateWithParam(woman);
        System.out.println(res3);
    }
}