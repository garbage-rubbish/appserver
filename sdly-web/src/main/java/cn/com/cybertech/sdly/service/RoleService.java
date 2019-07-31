package cn.com.cybertech.sdly.service;

import cn.com.cybertech.sdly.model.po.TpRole;

import java.util.List;

public interface RoleService {

    List<TpRole> findRoleByIds(List<Integer> ids);

    int saveRole(TpRole tpRole);

}
