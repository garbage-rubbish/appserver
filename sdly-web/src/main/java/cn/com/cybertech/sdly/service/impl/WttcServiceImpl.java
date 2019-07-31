package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.mapper.TdWttcMapper;
import cn.com.cybertech.sdly.model.po.TdWttc;
import cn.com.cybertech.sdly.service.WttcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WttcServiceImpl implements WttcService {

    @Autowired
    private TdWttcMapper wttcMapper;

    @Override
    public int insertWttc(TdWttc record) {
        return wttcMapper.insert(record);
    }
}
