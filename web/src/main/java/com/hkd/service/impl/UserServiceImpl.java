package com.hkd.service.impl;

import com.hkd.enums.ResultCode;
import com.hkd.exceptions.BusinessException;
import com.hkd.mapper.TpUserMapper;
import com.hkd.model.po.TpUser;
import com.hkd.model.po.TpUserExample;
import com.hkd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
