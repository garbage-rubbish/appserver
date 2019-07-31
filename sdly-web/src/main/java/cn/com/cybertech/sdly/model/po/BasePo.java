package cn.com.cybertech.sdly.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * 基础po
 * Created by huangkd on 2019/1/20.
 */
@Data
public abstract class BasePo<PK> implements PO<PK> {

    @ApiModelProperty(value = "创建时间")
    @Column(name = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "updateTime")
    private Date updateTime;


}
