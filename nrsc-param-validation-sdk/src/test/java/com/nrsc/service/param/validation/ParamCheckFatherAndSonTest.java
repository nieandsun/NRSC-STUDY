package com.nrsc.service.param.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.junit.Test;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-05-09
 * Description:
 */
@Slf4j
public class ParamCheckFatherAndSonTest {
    @Data
    public static class Dog implements NeedValidateBean {
        @NotBlank(message = "name不能为空")
        private String name;
        private int age;
        private List<Toy> toyList;
    }

    @Data
    public static class Toy implements NeedValidateBean {
        @NotBlank(message = "color不能为空")
        private String color;
    }

    public abstract static class Animal {
        @ParamsCheck
        String play(Dog dog, String test) {
            return "Animal play" + dog.toString();
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Component("person")
    public static class Person extends Animal {
        @Override
        @ParamsCheck
        public String play(Dog dog, String test) {
            if (dog.getToyList() != null) {
                dog.toyList.forEach(e -> ((Person) AopContext.currentProxy()).check(e));
            }
            return "Person play" + dog.toString();
        }

        @ParamsCheck //private Aspect切不到
        protected void check(Toy toy) {
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Component("man")
    public static class Man extends Animal {

    }


    @Configuration
    @ComponentScan("com.nrsc.service.param.validation")
    @EnableAspectJAutoProxy(exposeProxy = true) //指定使用CGLIB代理
    public static class Conf {
    }

    @Test
    public void test01() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Conf.class);
        Animal bean = ac.getBean(Man.class);
        Dog dog = new Dog();

        assertThatThrownBy(() -> bean.play(dog, "test")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name不能为空");
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Conf.class);
        Person bean = ac.getBean(Person.class);
        Dog dog = new Dog();
        dog.setName("yoyo");
        dog.setToyList(Arrays.asList(new Toy()));

        assertThatThrownBy(() -> bean.play(dog, "test")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("color不能为空");
    }
}
