package com.hkd.service;

import com.hkd.model.po.TpUser;

public interface UserService {
    TpUser findUserByMjjh(String mjjh);

    int saveUser(TpUser tpUser);
}
