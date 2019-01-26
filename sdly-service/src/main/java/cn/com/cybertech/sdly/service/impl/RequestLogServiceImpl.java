package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.model.po.RequestLog;
import cn.com.cybertech.sdly.service.RequestLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huangkd on 2019/1/21.
 */
@Service
public class RequestLogServiceImpl extends BaseServiceImpl<RequestLog,String> implements RequestLogService {


    @Override
    @Transactional
    @ChangeDataSource
    public String insert(RequestLog record) {
        return super.insert(record);


    }
}
