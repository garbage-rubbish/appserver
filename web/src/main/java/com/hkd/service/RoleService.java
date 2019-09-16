package com.hkd.service;

import com.hkd.model.po.TpRole;

import java.util.List;

public interface RoleService {

    List<TpRole> findRoleByIds(List<Integer> ids);

    int saveRole(TpRole tpRole);

}
