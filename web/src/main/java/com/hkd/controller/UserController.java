package com.hkd.controller;

import com.hkd.annotations.ChangeDataSource;
import com.hkd.model.dto.UserInfo;
import com.hkd.model.po.TpUser;
import com.hkd.result.PlatformResult;
import com.hkd.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Api
public class UserController {

    @Autowired
    private UserService userService;


    @ChangeDataSource("slaver")
    @GetMapping("/user/{mjjh}")
    public PlatformResult<UserInfo> getUserInfo(@PathVariable String mjjh){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        TpUser tpUser = userService.findUserByMjjh(mjjh);
        UserInfo userInfo=new UserInfo();
        userInfo.setType(UserInfo.Type.IN);
        BeanUtils.copyProperties(tpUser,userInfo);
        return PlatformResult.success(userInfo);

    }

    @PostMapping("/user")
    public PlatformResult<String> saveUserInfo(@RequestBody UserInfo userInfo){
        TpUser tpUser=new TpUser();

        BeanUtils.copyProperties(userInfo,tpUser);
        int i=userService.saveUser(tpUser);
        return PlatformResult.success(String.valueOf(i));

    }

}
