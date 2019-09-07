package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import cn.com.cybertech.sdly.mapper.TpUserMapper;
import cn.com.cybertech.sdly.model.po.TpUser;
import cn.com.cybertech.sdly.model.po.TpUserExample;
import cn.com.cybertech.sdly.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private TpUserMapper userMapper;


    @Override
    public TpUser findUserByMjjh(String mjjh) {
        TpUserExample example = new TpUserExample();
        example.createCriteria().andMjjhEqualTo(mjjh);
        List<TpUser> tpUsers = userMapper.selectByExample(example);
        if (!tpUsers.isEmpty()) {
            return tpUsers.get(0);
        }
        return null;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUser(TpUser tpUser) {
        TpUserExample tpUserExample = new TpUserExample();
        tpUserExample.or().andMjjhEqualTo(tpUser.getMjjh());
        tpUserExample.or().andSfzhEqualTo(tpUser.getSfzh());
        List<TpUser> tpUsers = userMapper.selectByExample(tpUserExample);
        if (!tpUsers.isEmpty()) {
            throw new BusinessException(ResultCode.RECORD_EXISTS);
        }
        try{
            userMapper.insert(tpUser);
        }catch (Exception e){
            log.error("execute sql error{}",tpUser.toString());
            throw new BusinessException(String.format("sql exception:%s",e.getMessage()));
        }
        int i = 1 / 0;
        return 0;
    }


}
