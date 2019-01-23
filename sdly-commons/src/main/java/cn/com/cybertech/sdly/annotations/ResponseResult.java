package cn.com.cybertech.sdly.annotations;

import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.result.Result;

import java.lang.annotation.*;

/**
 * Created by huangkd on 2019/1/23.
 */

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseResult {

    Class<? extends Result> value() default PlatformResult.class;

}
