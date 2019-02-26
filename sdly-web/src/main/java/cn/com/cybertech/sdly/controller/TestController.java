package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.annotations.Log;
import cn.com.cybertech.sdly.annotations.ResponseResult;
import cn.com.cybertech.sdly.mapper.RequestLogMapper;
import cn.com.cybertech.sdly.mapper.TestUserMapper;
import cn.com.cybertech.sdly.model.po.RequestLog;
import cn.com.cybertech.sdly.model.po.TestUser;
import cn.com.cybertech.sdly.model.po.User;
import cn.com.cybertech.sdly.model.qo.PageQo;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.RequestLogService;
import cn.com.cybertech.sdly.service.TestUserService;
import cn.com.cybertech.sdly.test.Demo;
import cn.com.cybertech.sdly.test.Demo1;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by huangkd on 2019/1/20.
 */
@ApiModel("测试")
@RestController
@ResponseResult
public class TestController {

    @Autowired
    private RequestLogService requestLogService;

    @Autowired
    private RequestLogMapper requestLogMapper;
   /* @Autowired
    private UserMapper userMapper;
*/

   @Autowired
   private TestUserService testUserService;


   @Autowired
   private TestUserMapper testUserMapper;

    @GetMapping("/test")
    @Log("测试")
    @ChangeDataSource("test2")
    public PlatformResult<RequestLog> test(@RequestParam String param1, String params2, PageQo pageQo){
        RequestLog requestLog=new RequestLog(new Date(),new Date(),"xxx","xxx","asd","asd","asd","asdas","aasd",111);
        requestLogMapper.insert(requestLog);
        return PlatformResult.success(requestLog);
    }

    @GetMapping("/test1")
    @ChangeDataSource("psms1")
    @Log("tset")
    @PreAuthorize("hasAuthority('ROLE_MJ')")
    public PlatformResult<String> test1(){
        //DataSourceContextHolder.setDataSourceKey("psms1");
        RequestLog requestLog=requestLogMapper.selectByPrimaryKey("2");
        //DataSourceContextHolder.remove();
        System.out.println(requestLog);

        return PlatformResult.success("sss");
    }

    @PostMapping("/test2")
    public PlatformResult<Demo> test2(@Validated @RequestBody Demo demo){
        return PlatformResult.success(demo);
    }

    @GetMapping("test3")
    public PlatformResult<User> test3(@RequestParam String param, @RequestParam Demo1 haha){
        return PlatformResult.success(new User());
    }
    @GetMapping("test4")
    @ChangeDataSource
    public PlatformResult<TestUser> test4(){
        TestUser testUser = testUserMapper.selectByPrimaryKey("1");
        return PlatformResult.success(testUser);
    }

  /*  @PostMapping("/test2")
    @ChangeDataSource("test")
    @Log("插入用户")
    public String test2(@RequestBody User user){
        return userService.insert(user);
    }
*/



}
