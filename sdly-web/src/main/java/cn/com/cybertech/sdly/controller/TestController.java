package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.annotations.Log;
import cn.com.cybertech.sdly.model.po.RequestLog;
import cn.com.cybertech.sdly.model.qo.PageQo;
import cn.com.cybertech.sdly.service.impl.BaseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangkd on 2019/1/20.
 */
@ApiModel("测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BaseServiceImpl<RequestLog,String> baseService;


    @GetMapping("/tset")
    @Log("测试")

    public PageQo test(@RequestParam String param1, String params2, PageQo pageQo){
        System.out.println(baseService);
        return pageQo;
    }


}
