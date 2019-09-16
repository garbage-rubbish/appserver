package com.hkd.service;

import com.hkd.model.po.TpUserRole;

import java.util.List;

public interface UserRoleService {

    List<TpUserRole> findUserRoleByUserId(Integer id);
}
