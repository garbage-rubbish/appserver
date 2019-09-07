package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.model.dto.UserInfo;
import cn.com.cybertech.sdly.model.po.TpUser;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.UserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api
public class UserController {

    @Autowired
    private UserService userService;


    @ChangeDataSource("slaver")
    @GetMapping("/user/{mjjh}")
    public Map<String,Object> getUserInfo(@PathVariable String mjjh){
        TpUser tpUser = userService.findUserByMjjh(mjjh);
        UserInfo userInfo=new UserInfo();
        userInfo.setType(UserInfo.Type.IN);
        BeanUtils.copyProperties(tpUser,userInfo);
        HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("sss",new ArrayList<>());
        return objectObjectHashMap;

    }

    @PostMapping("/user")
    public PlatformResult<String> saveUserInfo(@RequestBody UserInfo userInfo){
        TpUser tpUser=new TpUser();

        BeanUtils.copyProperties(userInfo,tpUser);
        int i=userService.saveUser(tpUser);
        return PlatformResult.success(String.valueOf(i));

    }

}
