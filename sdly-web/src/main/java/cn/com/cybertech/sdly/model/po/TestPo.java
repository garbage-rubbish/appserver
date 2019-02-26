package cn.com.cybertech.sdly.model.po;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by huangkd on 2019/1/20.
 */
@Data
@ApiModel("ceshi")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestPo extends BasePo<String> {

    private static final long serialVersionUID = 8443430102327534161L;
    private String id;




}
