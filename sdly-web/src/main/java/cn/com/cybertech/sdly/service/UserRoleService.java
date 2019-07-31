package cn.com.cybertech.sdly.service;

import cn.com.cybertech.sdly.model.po.TpUserRole;

import java.util.List;

public interface UserRoleService {

    List<TpUserRole> findUserRoleByUserId(Integer id);
}
