package com.nrsc.service.param.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.validation.constraints.NotBlank;

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
public class ParamCheckBaseTest {


    @Data
    public static class Dog implements NeedValidateBean {
        @NotBlank(message = "name不能为空")
        private String name;
        private int age;
    }

    @Component("people")
    public static class People {
        @ParamsCheck
        public String play(Dog dog) {
            return "play with " + dog.name;
        }
    }


    @Configuration
    @ComponentScan("com.nrsc.service.param.validation")
    public static class Conf {
    }


    @Test
    public void test01() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Conf.class);
        People bean = ac.getBean(People.class);
        assertThatThrownBy(() -> bean.play(new Dog())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name不能为空");
    }
}
