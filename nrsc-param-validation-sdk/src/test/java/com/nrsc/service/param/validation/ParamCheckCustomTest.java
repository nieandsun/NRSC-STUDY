package com.nrsc.service.param.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-05-09
 * Description:
 */
public class ParamCheckCustomTest {

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


    @Data
    public static class Dog implements NeedValidateBean {
        @AgeCheck(message = "未满18周岁")
        private Integer age;
    }

    @Component("people123")
    public static class People {
        @ParamsCheck
        public String play(Dog dog) {
            return "play with " + dog.toString();
        }
    }


    @Configuration
    @ComponentScan("com.nrsc.service.param.validation")
    public static class Conf {
    }


    @Test
    public void test01() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ParamCheckBaseTest.Conf.class);
        People bean = (People) ac.getBean("people123");

        assertThatThrownBy(() -> bean.play(new Dog())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("未满18周岁");

        Dog dog = new Dog();
        dog.age = 17;
        assertThatThrownBy(() -> bean.play(new Dog())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("未满18周岁");
    }
}
