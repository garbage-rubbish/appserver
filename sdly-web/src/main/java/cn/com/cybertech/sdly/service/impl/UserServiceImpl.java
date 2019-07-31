package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import cn.com.cybertech.sdly.mapper.TpUserMapper;
import cn.com.cybertech.sdly.model.po.TpUser;
import cn.com.cybertech.sdly.model.po.TpUserExample;
import cn.com.cybertech.sdly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TpUserMapper userMapper;



    @Override
    @ChangeDataSource
    public TpUser findUserByMjjh(String mjjh) {
        TpUserExample example=new TpUserExample();
        example.createCriteria().andMjjhEqualTo(mjjh);
        List<TpUser> tpUsers = userMapper.selectByExample(example);
        if(!tpUsers.isEmpty()){
            return tpUsers.get(0);
        }
        return null;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUser(TpUser tpUser) {
         userMapper.insert(tpUser);
         return 0;
    }


}
