package cn.com.cybertech.sdly.service;

import cn.com.cybertech.sdly.model.po.User;

/**
 * Created by huangkd on 2019/1/22.
 */
public interface UserService {

    String insert (User user);

    User findByUsername(String username);

}
