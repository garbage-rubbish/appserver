package cn.com.cybertech.sdly.aop;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.config.datasource.DataSourceContextHolder;
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
        //如果 datasource key 存在直接使用
        if(DataSourceContextHolder.dataSourceIds.contains(value)){
            DataSourceContextHolder.setDataSourceKey(value);
        }else{
            log.warn("数据源:[{}],不存在使用默认数据源");
        }
        try {
           return point.proceed();
        } finally {
            DataSourceContextHolder.remove();
        }
    }
}
