package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.mapper.UserMapper;
import cn.com.cybertech.sdly.model.other.LoginUser;
import cn.com.cybertech.sdly.model.po.User;
import cn.com.cybertech.sdly.service.AuthService;
import cn.com.cybertech.sdly.service.UserService;
import cn.com.cybertech.sdly.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by huangkd on 2019/1/26.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginUser loginUser) {
        User byUsername = userService.findByUsername(loginUser.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return JwtTokenUtil.createToken(byUsername);
    }

    @Override
    public boolean logout() {
        return false;
    }
}
