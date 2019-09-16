package com.hkd.controller;

import com.hkd.mapper.RoadMapper;
import com.hkd.model.dto.Dict;
import com.hkd.model.po.Road;
import com.hkd.model.po.RoadExample;
import com.hkd.result.PlatformResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictController {

    @Autowired
    private RoadMapper roadMapper;

    @GetMapping("/dict/tcdd")
    public PlatformResult<Dict> getRoad(){
        RoadExample roadExample=new RoadExample();
        List<Road> roads = roadMapper.selectByExample(roadExample);
        Dict dict=new Dict();
        dict.setName("tcdd");
        dict.setVersion("无");
        roads.forEach(road -> dict.getData().put(road.getXzqh()+road.getDldm(),road.getDlmc()));
        return PlatformResult.success(dict);
    }

}
