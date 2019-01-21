package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.annotations.Log;
import cn.com.cybertech.sdly.mapper.UserMapper;
import cn.com.cybertech.sdly.model.po.RequestLog;
import cn.com.cybertech.sdly.model.po.User;
import cn.com.cybertech.sdly.model.qo.PageQo;
import cn.com.cybertech.sdly.service.RequestLogService;
import cn.com.cybertech.sdly.service.impl.BaseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangkd on 2019/1/20.
 */
@ApiModel("测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RequestLogService requestLogService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    @Log("测试")
    @ChangeDataSource()
    public RequestLog test(@RequestParam String param1, String params2, PageQo pageQo){
        return requestLogService.selectByPk("10");
    }

    @GetMapping("/test1")
    @ChangeDataSource("zhuma_user")
    public User test1(){
        User user=userMapper.selectById("1");
        return user;
    }


}
