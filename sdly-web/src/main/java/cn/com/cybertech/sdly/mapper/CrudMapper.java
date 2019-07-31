package cn.com.cybertech.sdly.mapper;


/**
 * Created by huangkd on 2019/1/19.
 * 通用增删改查mapper
 */
public interface CrudMapper<T>
        extends DeleteMapper<T>,
        UpdateMapper<T>,
        SelectMapper<T>,
        InsertMapper<T> {


}
