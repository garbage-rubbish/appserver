package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.model.dto.UserInfo;
import cn.com.cybertech.sdly.model.po.TpUser;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Api
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/{mjjh}")
    public PlatformResult<UserInfo> getUserInfo(@PathVariable String mjjh){
        TpUser tpUser = userService.findUserByMjjh(mjjh);
        UserInfo userInfo=new UserInfo();
        userInfo.setType(UserInfo.Type.IN);
        BeanUtils.copyProperties(tpUser,userInfo);

        return PlatformResult.success(userInfo);
    }

}
