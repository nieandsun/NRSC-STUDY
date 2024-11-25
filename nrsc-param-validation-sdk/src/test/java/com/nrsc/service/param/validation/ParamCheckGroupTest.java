package com.nrsc.service.param.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.validation.constraints.NotBlank;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.nrsc.service.param.validation.ParamCheckGroupTest.Group.Insert;
import com.nrsc.service.param.validation.ParamCheckGroupTest.Group.Update;

import lombok.Data;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-05-09
 * Description:
 */
public class ParamCheckGroupTest {

    public static class Group {
        /**
         * 入账组
         */
        public interface Insert {
        }


        /**
         * 更新账单信息组
         */
        public interface Update {
        }


    }


    @Data
    public static class Dog implements NeedValidateBean {
        @NotBlank(message = "name不能为空", groups = {Insert.class})
        private String name;

        @NotBlank(message = "age不能为空", groups = {Update.class})
        private String age;

        @NotBlank(message = "color不能为空")
        private String color;
    }

    @Component("people11")
    public static class People {
        @ParamsCheck
        public String playInsert(@Validated(Insert.class) Dog dog) {
            return "play with " + dog.name;
        }

        @ParamsCheck
        public String playUpdate(@Validated(Update.class) Dog dog) {
            return "play with " + dog.name;
        }

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
        People bean = (People) ac.getBean("people11");

        assertThatThrownBy(() -> bean.playInsert(new Dog())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name不能为空");


        assertThatThrownBy(() -> bean.playUpdate(new Dog())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("age不能为空");


        assertThatThrownBy(() -> bean.play(new Dog())).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("color不能为空");
    }
}
