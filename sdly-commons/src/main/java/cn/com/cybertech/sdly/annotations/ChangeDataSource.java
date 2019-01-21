package cn.com.cybertech.sdly.annotations;

import java.lang.annotation.*;

/**
 * Created by huangkd on 2019/1/21.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeDataSource {

    String value() default "master";
}
