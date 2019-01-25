package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.mapper.UserMapper;
import cn.com.cybertech.sdly.model.po.User;
import cn.com.cybertech.sdly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by huangkd on 2019/1/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    //@Transactional
    public String insert(User user) {
        /*user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int i =userMapper.insert(user);
        System.out.println(i);
        return user.getId();*/
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return User.builder().id(1).depart("123123123").idCard("321003001").password("123123").username("cyber")
                .roles(Arrays.asList(new String[]{"ROLE_MJ","ROLE_LD"})).build();
    }
}
