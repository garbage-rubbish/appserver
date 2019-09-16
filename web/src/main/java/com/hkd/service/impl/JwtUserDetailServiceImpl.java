package com.hkd.service.impl;

import com.hkd.model.JwtUser;
import com.hkd.model.po.TpRole;
import com.hkd.model.po.TpUser;
import com.hkd.model.po.TpUserRole;
import com.hkd.service.RoleService;
import com.hkd.service.UserRoleService;
import com.hkd.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangkd on 2019/1/24.
 */
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private  UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String s) {
        TpUser user=userService.findUserByMjjh(s);
        if(user==null){
            return null;
        }
        List<TpUserRole> userRoles = userRoleService.findUserRoleByUserId(user.getId());
        List<Integer> roleIds= Lists.newArrayList();
        userRoles.forEach(userRole->roleIds.add(userRole.getRoleid()));
        List<TpRole> roleByIds = roleService.findRoleByIds(roleIds);

        return new JwtUser(user,roleByIds);
    }
}
