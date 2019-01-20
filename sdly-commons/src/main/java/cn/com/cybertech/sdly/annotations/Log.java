package cn.com.cybertech.sdly.annotations;

import java.lang.annotation.*;

/**
 * log注解 被注解
 * Created by huangkd on 2019/1/20.
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String value() default "";

}
