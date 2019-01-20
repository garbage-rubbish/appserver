package cn.com.cybertech.sdly.service;

/**
 * Created by huangkd on 2019/1/19.
 * 通用增删改查服务
 */
public interface CrudService<E, PK> extends
        DeleteService<PK>,
        UpdateService<E, PK>,
        SelectService<E, PK>,
        InsertService<E, PK> {


}
