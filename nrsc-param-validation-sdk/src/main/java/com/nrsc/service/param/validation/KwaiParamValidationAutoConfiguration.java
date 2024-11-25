package com.nrsc.service.param.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-04-19
 * Description:
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true) //将代理的目标对象暴露出来
public class KwaiParamValidationAutoConfiguration {

    @Bean
    public ServiceParamValidateAspect serviceParamValidateAspect() {
        return new ServiceParamValidateAspect();
    }
}
