package com.hkd.service;

import com.hkd.model.qo.PageQo;
import com.hkd.model.vo.PageVo;

import java.util.List;

/**
 * Created by huangkd on 2019/1/19.
 * 通用查询服务
 */
public interface SelectService<E,PK> {

    /**
     * 通过主键查询数据
     * @param id
     * @return
     */
    E selectByPk(PK id);

    /**
     * 查询列表
     * @return
     */
    List<E> selectList();


    /**
     * 根据主键查询列表
     * @param ids
     * @return
     */
    List<E> selectByPks(Iterable<PK> ids);


    /**
     * 分页查询
     * @param pageQo
     * @return pageVo
     */
    PageVo<E> selectPage(PageQo pageQo);

}
