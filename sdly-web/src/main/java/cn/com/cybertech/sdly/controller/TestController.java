package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.annotations.Log;
import cn.com.cybertech.sdly.mapper.RequestLogMapper;
import cn.com.cybertech.sdly.mapper.UserMapper;
import cn.com.cybertech.sdly.model.po.RequestLog;
import cn.com.cybertech.sdly.model.po.User;
import cn.com.cybertech.sdly.model.qo.PageQo;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.RequestLogService;
import cn.com.cybertech.sdly.service.UserService;
import cn.com.cybertech.sdly.service.impl.BaseServiceImpl;
import cn.com.cybertech.sdly.service.impl.UserServiceImpl;
import cn.com.cybertech.sdly.test.Demo;
import cn.com.cybertech.sdly.test.Demo1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by huangkd on 2019/1/20.
 */
@ApiModel("测试")
@RestController
public class TestController {

    @Autowired
    private RequestLogService requestLogService;

    @Autowired
    private RequestLogMapper requestLogMapper;
   /* @Autowired
    private UserMapper userMapper;
*/
   @Autowired
   private UserService userService;

    @GetMapping("/test")
    @Log("测试")
    @ChangeDataSource("test2")
    public PlatformResult test(@RequestParam String param1, String params2, PageQo pageQo){
        RequestLog requestLog=new RequestLog(new Date(),new Date(),"xxx","xxx","asd","asd","asd","asdas","aasd",111);
        requestLogMapper.insert(requestLog);
        return PlatformResult.success(requestLog);
    }

    @GetMapping("/test1")
    @ChangeDataSource("zhuma_user")
    @Log("tset")
    public String test1(){
      //  User user=userService.selectById("1");
        //return user;
        return "sdfsdf";
    }

    @PostMapping("/test2")
    public PlatformResult test2(@Validated @RequestBody Demo demo){
        return PlatformResult.success(demo);
    }

    @GetMapping("test3")
    public PlatformResult test3(@RequestParam String param,@RequestParam Demo1 haha){
        return PlatformResult.success(param+haha);
    }

  /*  @PostMapping("/test2")
    @ChangeDataSource("test")
    @Log("插入用户")
    public String test2(@RequestBody User user){
        return userService.insert(user);
    }
*/



}
