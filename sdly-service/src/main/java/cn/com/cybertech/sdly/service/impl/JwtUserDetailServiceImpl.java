package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import cn.com.cybertech.sdly.model.JwtUser;
import cn.com.cybertech.sdly.model.po.User;
import cn.com.cybertech.sdly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by huangkd on 2019/1/24.
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user=userService.findByUsername(s);
        if(user==null){
            return null;
        }
        return new JwtUser(user);
    }
}
