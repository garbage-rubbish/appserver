package com.hkd.aop;

import com.hkd.annotations.ChangeDataSource;
import com.hkd.config.datasource.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by huangkd on 2019/1/21.
 */

@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    @Pointcut("@annotation(com.hkd.annotations.ChangeDataSource)")
    public void pointCut(){}



    @Around("pointCut()")
    public Object post(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature= (MethodSignature) point.getSignature();
        ChangeDataSource annotation = methodSignature.getMethod().getAnnotation(ChangeDataSource.class);
        String value = annotation.value();
        DataSourceContextHolder.setDataSourceKey(value);
        try {
           return point.proceed();
        } finally {
            DataSourceContextHolder.remove();
        }
    }
}
