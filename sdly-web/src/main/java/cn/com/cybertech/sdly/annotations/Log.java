package cn.com.cybertech.sdly.annotations;

import java.lang.annotation.*;

/**
 * log 记录请求日志 只能在@requestmapping 方法注解
 * 被注解的方法和log记录不是同一个数据源会出错
 * Created by huangkd on 2019/1/20.
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String value() default "";

}
