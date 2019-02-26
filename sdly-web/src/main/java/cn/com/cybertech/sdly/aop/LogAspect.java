package cn.com.cybertech.sdly.aop;

import cn.com.cybertech.sdly.annotations.Log;
import cn.com.cybertech.sdly.config.datasource.DataSourceContextHolder;
import cn.com.cybertech.sdly.model.po.RequestLog;
import cn.com.cybertech.sdly.service.RequestLogService;
import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangkd on 2019/1/20.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(cn.com.cybertech.sdly.annotations.Log)")
    public void logPointCut(){}

    @Autowired
    private RequestLogService requestLogService;

    @Around("logPointCut()")
    public Object handleRequestLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //requestAttributes 在请求子线程中为空，可以开启子线程共享解决
        //RequestContextHolder.setRequestAttributes(requestAttributes,true);
        HttpServletRequest request = requestAttributes.getRequest();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName=proceedingJoinPoint.getSignature().getName();
        String ip=request.getRemoteAddr();
        String params="";
        Gson gson= new Gson();
        if(proceedingJoinPoint.getArgs()!=null&&proceedingJoinPoint.getArgs().length>0){
            StringBuilder paramsSb=new StringBuilder();
            for(int i=0;i<proceedingJoinPoint.getArgs().length;i++){
                String p=gson.toJson(proceedingJoinPoint.getArgs()[i]);
                paramsSb.append(p);
            }
            params=paramsSb.toString();
        }

        //params=gson.toJson(request.getParameterMap());
        String reqUrl=request.getRequestURL().toString();
        Stopwatch stopwatch=Stopwatch.createStarted();
        Object resultObj = proceedingJoinPoint.proceed();
        long spendTime=stopwatch.elapsed(TimeUnit.MILLISECONDS);
        String resultStr=gson.toJson(resultObj);
        Log annotation = getAnnotation(proceedingJoinPoint, Log.class);
        String desc=annotation.value();
        Date date=new Date();
        RequestLog requestLog=new RequestLog(date,date,className,methodName,ip,params,reqUrl,desc,resultStr,spendTime);
        //手动切换到主数据源
        log.info("request log:{}",requestLog.toString());
        DataSourceContextHolder.setDataSourceKey("master");
       // requestLogService.insert(requestLog);
        return resultObj;

    }

     <T extends Annotation> T getAnnotation(JoinPoint joinPoint,Class<T> tClass){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(tClass);
    }




}
