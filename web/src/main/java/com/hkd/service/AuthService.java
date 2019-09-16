package com.hkd.service;

/**
 * Created by huangkd on 2019/1/25.
 */
public interface AuthService {

    /**
     * 登陆成功返回token
     * @param loginUser
     * @return
     */
    String login(String username, String password);

    void logout();

}
