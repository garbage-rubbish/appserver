package cn.com.cybertech.sdly.model.po;

import cn.com.cybertech.sdly.model.Model;

import java.util.Date;

/**
 * 每个需要持久化的表都需要id,createtime,updatetime字段
 * Created by huangkd on 2019/1/20.
 */
public interface PO<PK> extends Model {

    PK getId();

    void setId(PK pk);

    Date getCreateTime();

    void setCreateTime(Date date);

    Date getUpdateTime();

    void setUpdateTime(Date date);

}
