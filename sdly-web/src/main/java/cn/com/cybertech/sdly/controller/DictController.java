package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.mapper.RoadMapper;
import cn.com.cybertech.sdly.model.dto.Dict;
import cn.com.cybertech.sdly.model.po.Road;
import cn.com.cybertech.sdly.model.po.RoadExample;
import cn.com.cybertech.sdly.result.PlatformResult;
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
