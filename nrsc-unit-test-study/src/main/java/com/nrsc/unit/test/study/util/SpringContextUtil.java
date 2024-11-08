package com.nrsc.unit.test.study.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author sunchuan
 * Created on 2024-11-06
 */

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取实现某接口的所有springBean
     *
     * @param type 接口类型
     * @return <beanName, bean>
     */
    public <T> Map<String, T> getBeanOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * 通过beanName获取相应的bean
     *
     * @param name beanName
     * @param requiredType bean类型
     * @param <T> 返回类型
     */
    public static <T> T getBeanByName(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 通过beanName获取相应的bean
     *
     * @param name beanName
     */
    public Object getBeanByName(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 根据class 获取对应的bean  注意防止  NoUniqueBeanDefinitionException
     *
     * @param clazz 类型
     */
    public static <T> T getBeanByType(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
