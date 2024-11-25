package com.nrsc.service.param.validation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sunchuan <sunchuan@kuaishou.com>
 * Created on 2023-04-18
 * Description: service层参数校验拦截器
 */
@Slf4j
@Aspect
public class ServiceParamValidateAspect {

    /**
     * service 层入参校验
     *
     * @param joinPoint 切点
     */
    @Around("@annotation(paramsCheck)")
    public Object serviceParamCheckAround(ProceedingJoinPoint joinPoint, ParamsCheck paramsCheck) throws Throwable {

        //获取当前类方法
        Method method;
        try {
            //获取当前类方法
            method = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(),
                    ((MethodSignature) joinPoint.getSignature()).getParameterTypes());
        } catch (NoSuchMethodException noSuchMethodException) {
            //如果获取失败,获取父类方法
            method = joinPoint.getTarget().getClass().getSuperclass()
                    .getDeclaredMethod(joinPoint.getSignature().getName(),
                            ((MethodSignature) joinPoint.getSignature()).getParameterTypes());
        }
        //参数值对象
        Object[] objects = joinPoint.getArgs();
        //参数
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < objects.length; i++) {
            Object arg = objects[i];
            if (arg == null) {
                continue;
            }
            // 判断是否为 com.nrsc.i18n.ad.dsp.util.paramvalidator.NeedValidateBean 的子类
            boolean isChildClass = NeedValidateBean.class.isAssignableFrom(arg.getClass());

            if (isChildClass) {
                log.info("service层参数校验,method = {}, arg = {}", method.getName(), arg.toString());

                //获取Validated中的值~~~分组信息
                Validated annotation = AnnotationUtils.findAnnotation(parameters[i], Validated.class);

                String validResult;
                if (annotation == null) {
                    // 参数校验
                    validResult = BeanValidateUtil.validateWithParam(arg);
                } else {
                    validResult = BeanValidateUtil.validateWithParam(arg, annotation.value());
                }
                //如果校验失败,抛出异常
                if (validResult != null && validResult.length() > 0) {
                    log.error("service层参数校验失败,method = {}, arg = {}", method.getName(), arg.toString());
                    throw new IllegalArgumentException(validResult);
                }
            }
        }
        return joinPoint.proceed();
    }
}
