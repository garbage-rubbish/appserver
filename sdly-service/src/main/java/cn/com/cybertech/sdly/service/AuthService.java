package cn.com.cybertech.sdly.service;

import cn.com.cybertech.sdly.model.other.LoginUser;

/**
 * Created by huangkd on 2019/1/25.
 */
public interface AuthService {

    /**
     * 登陆成功返回token
     * @param loginUser
     * @return
     */
    String login(LoginUser loginUser);

    boolean logout();

}
