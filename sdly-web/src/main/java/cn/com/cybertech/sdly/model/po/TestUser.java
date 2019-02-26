package cn.com.cybertech.sdly.model.po;

import cn.com.cybertech.sdly.enums.GenderEnum;
import cn.com.cybertech.sdly.handler.CodeValueEnumTypeHandler;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by huangkd on 2019/2/26.
 */
@Data
@NameStyle(Style.normal)
@Table(name = "td_testUser")
public class TestUser implements PO<String> {

    private static final long serialVersionUID = -6345491120414072759L;
    @Id
    private String id;
    private String name;
    private int age;
    private String address;
    private GenderEnum gender;
    private Date createTime;
    private Date updateTime;

    @Override
    public Date getCreateTime() {
        return null;
    }

    @Override
    public void setCreateTime(Date date) {

    }

    @Override
    public Date getUpdateTime() {
        return null;
    }

    @Override
    public void setUpdateTime(Date date) {

    }
}
