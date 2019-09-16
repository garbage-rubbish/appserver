package com.hkd.service.impl;

import com.hkd.mapper.TdWttcMapper;
import com.hkd.model.po.TdWttc;
import com.hkd.service.WttcService;
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
