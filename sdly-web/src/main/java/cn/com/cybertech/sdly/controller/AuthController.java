package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.model.other.LoginUser;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangkd on 2019/1/25.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public PlatformResult<String> login(@Validated @RequestBody LoginUser loginUser){
        return PlatformResult.success(authService.login(loginUser));
    }

}
