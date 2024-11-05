package com.nrsc.unit.test.study;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author : sunchuan
 * @date : 2024/11/5
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Demo1StaticConstant.class})
public class Demo1StaticConstantTest {
    @Test
    public void testDemoMethod() throws Exception {
        //属性1:要mock的静态常量所在类的类型
        //属性2:要mock的静态常量名称
        //属性3:要mock的静态常量所在类的类型（或要mock的静态常量所在的对象）
        //属性4:要设置的值
        MemberModifier.field(Demo1StaticConstant.class, "DEMO_CONSTANT").set(Demo1StaticConstant.class, "huawei");

        String result1 = Demo1StaticConstant.demoMethod("huawei");
        Assert.assertEquals(result1, "111");
        String result2 = Demo1StaticConstant.demoMethod("xiaomi");
        Assert.assertEquals(result2, "222");
    }


    @Test
    public void testDemoMethod2() throws Exception {
        //属性1:要mock的静态常量所在类的类型
        //属性2:要mock的静态常量名称
        //属性3:要mock的静态常量所在类的类型（或要mock的静态常量所在的对象）
        //属性4:要设置的值
        Demo1StaticConstant demo = new Demo1StaticConstant();
        MemberModifier.field(Demo1StaticConstant.class, "DEMO_CONSTANT").set(demo, "huawei");

        String result1 = demo.demoMethod2("huawei");
        Assert.assertEquals(result1, "111");
        String result2 = demo.demoMethod2("xiaomi");
        Assert.assertEquals(result2, "222");
    }
}
