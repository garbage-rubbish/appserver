package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.mapper.UserMapper;
import cn.com.cybertech.sdly.model.po.User;
import cn.com.cybertech.sdly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int i =userMapper.insert(user);
        System.out.println(i);
        return user.getId();
    }
}
