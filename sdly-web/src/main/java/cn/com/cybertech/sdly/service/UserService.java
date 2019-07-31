package cn.com.cybertech.sdly.service;

import cn.com.cybertech.sdly.model.po.TpUser;

public interface UserService {
    TpUser findUserByMjjh(String mjjh);

    int saveUser(TpUser tpUser);
}
