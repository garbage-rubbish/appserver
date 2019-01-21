package cn.com.cybertech.sdly.mapper;

import cn.com.cybertech.sdly.model.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by huangkd on 2019/1/21.
 */
public interface UserMapper {

    User selectById(@Param("id") String id);
}
