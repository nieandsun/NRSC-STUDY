package com.nrsc.unit.test.study.demo;

import java.util.Objects;

public class Demo1StaticConstant {

    /***
     * 这里的类型不能是基本类型或String哟 ~~ why（？？？）
     */
    private static final Object DEMO_CONSTANT = "xiaomi";

    /***
     * 静态方法
     * @param param
     * @return
     */
    public static String demoMethod(Object param) {

        if (Objects.equals(param, DEMO_CONSTANT)) {
            return "111";
        }
        return "222";
    }

    /***
     * 非静态方法
     * @param param
     * @return
     */
    public String demoMethod2(Object param) {

        if (Objects.equals(param, DEMO_CONSTANT)) {
            return "111";
        }
        return "222";
    }
}

