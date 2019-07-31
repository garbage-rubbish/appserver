package cn.com.cybertech.sdly.model.dto;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

@Data
public class Dict {

    private String name;
    private String version;
    private Map<String,String> data= Maps.newHashMap();



}
