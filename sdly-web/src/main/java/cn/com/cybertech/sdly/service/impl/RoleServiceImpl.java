package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.mapper.TpRoleMapper;
import cn.com.cybertech.sdly.model.po.TpRole;
import cn.com.cybertech.sdly.model.po.TpRoleExample;
import cn.com.cybertech.sdly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private TpRoleMapper roleMapper;

    @Override
    public List<TpRole> findRoleByIds(List<Integer> ids) {
        if(ids == null || ids.isEmpty()){
            return Collections.emptyList();
        }

        TpRoleExample tpRoleExample=new TpRoleExample();
        tpRoleExample.createCriteria().andIdIn(ids);
        return roleMapper.selectByExample(tpRoleExample);
    }

    @Override
    public int saveRole(TpRole tpRole) {
        return roleMapper.insert(tpRole);
    }
}
