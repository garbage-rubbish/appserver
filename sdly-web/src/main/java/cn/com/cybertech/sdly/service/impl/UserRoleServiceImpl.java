package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.mapper.TpUserRoleMapper;
import cn.com.cybertech.sdly.model.po.TpUserRole;
import cn.com.cybertech.sdly.model.po.TpUserRoleExample;
import cn.com.cybertech.sdly.service.UserRoleService;
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
