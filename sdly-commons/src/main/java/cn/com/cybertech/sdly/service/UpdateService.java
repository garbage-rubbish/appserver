package cn.com.cybertech.sdly.service;

/**
 * Created by huangkd on 2019/1/19.
 * 通用更新接口
 */
public interface UpdateService<E,PK> {


    /**
     *
     * @param pk
     * @param record
     * @return
     */
    int updateByPk(PK pk,E record);


    /**
     * 保存修改
     * @param record
     * @return
     */
    PK saveOrUpdate(E record);

}
