package com.nrsc.service.param.validation;

import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-04-19
 * Description:
 */
public class FindAnnotationTest {

    public abstract static class Demo1 {

        public String test1() {
            return test11();
        }

        @ParamsCheck
        protected abstract String test11();
    }

    public static class Demo2 extends Demo1 {

        @Override
        protected String test11() {
            return "112233";
        }
    }

    @Test
    public void findAnnotationTest() throws Exception {
        Demo2 demo2 = new Demo2();
        String s = demo2.test11();
        System.out.println(s);

        Method test11 = demo2.getClass().getDeclaredMethod("test11");

        ParamsCheck annotation = AnnotationUtils.findAnnotation(test11, ParamsCheck.class);
        System.out.println(annotation != null);
    }
}
