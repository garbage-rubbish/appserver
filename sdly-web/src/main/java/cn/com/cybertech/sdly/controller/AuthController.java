package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.model.other.LoginUser;
import cn.com.cybertech.sdly.publish.LoginEvent;
import cn.com.cybertech.sdly.publish.Publisher;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangkd on 2019/1/25.
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;


    @Autowired
   private Publisher publisher;

    @PostMapping(value = "/login")
    public PlatformResult<String> login(@Validated @RequestBody LoginUser loginUser){
        log.info("login:{}",loginUser.toString());
        publisher.publish(new LoginEvent(this,loginUser));
        log.info("publish login event");
        return PlatformResult.success(authService.login(loginUser.getUsername(),loginUser.getPassword()));
    }

    @GetMapping("/test")
    public PlatformResult<String> test(){
        return PlatformResult.success("sss");
    }

    @GetMapping("/test1")
    public PlatformResult<String> test1(){
        return PlatformResult.success("sss");
    }
}

