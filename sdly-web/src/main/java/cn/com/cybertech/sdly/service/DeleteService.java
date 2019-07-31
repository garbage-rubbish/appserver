package cn.com.cybertech.sdly.service;

/**
 * Created by huangkd on 2019/1/19.
 *
 * 通用删除服务
 */
public interface DeleteService<PK> {

    /**
     * 根据主键删除记录
     * @param id
     * @return 受影响的行数
     */
    int delete(PK id);

    /**
     *根据多个主键批量删除 记录
     * @param ids
     * @return 受影响的行数
     */
    int deleteByPks(Iterable<PK> ids);



}
