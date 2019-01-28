package cn.com.cybertech.sdly.aop;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.config.datasource.DataSourceContextHolder;
import cn.com.cybertech.sdly.exceptions.BusinessException;
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

    @Pointcut("@annotation(cn.com.cybertech.sdly.annotations.ChangeDataSource)")
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
