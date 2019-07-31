package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.model.JwtUser;
import cn.com.cybertech.sdly.model.po.TpRole;
import cn.com.cybertech.sdly.model.po.TpUser;
import cn.com.cybertech.sdly.model.po.TpUserRole;
import cn.com.cybertech.sdly.service.RoleService;
import cn.com.cybertech.sdly.service.UserRoleService;
import cn.com.cybertech.sdly.service.UserService;
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
