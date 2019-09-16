package com.hkd.service.impl;

import com.hkd.mapper.TpUserRoleMapper;
import com.hkd.model.po.TpUserRole;
import com.hkd.model.po.TpUserRoleExample;
import com.hkd.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private TpUserRoleMapper userRoleMapper;

    @Override
    public List<TpUserRole> findUserRoleByUserId(Integer id) {
        TpUserRoleExample example=new TpUserRoleExample();
        example.createCriteria().andUseridEqualTo(id);
        return userRoleMapper.selectByExample(example);
    }
}
