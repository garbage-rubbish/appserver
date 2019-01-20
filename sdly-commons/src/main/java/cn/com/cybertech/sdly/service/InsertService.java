package cn.com.cybertech.sdly.service;

/**
 * Created by huangkd on 2019/1/19.
 * 通用新增
 */
public interface InsertService<E,PK> {

    /**
     * 插入 返回主键
     * @param record
     * @return 添加数据的主键
     */
    PK insert(E record);

}
