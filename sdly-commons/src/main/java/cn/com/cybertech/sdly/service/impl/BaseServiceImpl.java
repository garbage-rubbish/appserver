package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.common.mapper.CrudMapper;
import cn.com.cybertech.sdly.model.po.PO;
import cn.com.cybertech.sdly.model.qo.PageQo;
import cn.com.cybertech.sdly.model.vo.PageVo;
import cn.com.cybertech.sdly.service.CrudService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by huangkd on 2019/1/20.
 */
@Service
@Transactional
public abstract class BaseServiceImpl<E extends PO<PK>,PK> implements CrudService<E,PK> {

    @Autowired
    private CrudMapper<E> crudMapper;

    @Override
    public int delete(PK id) {
        checkNotNull(id);
        return crudMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByPks(Iterable<PK> ids) {
        checkNotNull(ids);
        String id=iterableToSpitStr(ids,",");
        if(id==null){
            return 0;
        }
        return crudMapper.deleteByIds(id);
    }

    @Override
    public PK insert(E record) {
        crudMapper.insert(record);
        return record.getId();

    }

    @Override
    public E selectByPk(PK id) {
        return null;
    }

    @Override
    public List<E> selectList() {
        return null;
    }

    @Override
    public List<E> selectByPks(Iterable<PK> ids) {
        return null;
    }

    @Override
    public PageVo<E> selectPage(PageQo pageQo) {
        return null;
    }

    @Override
    public int updateByPk(PK pk, E record) {
        return 0;
    }

    @Override
    public PK saveOrUpdate(E record) {
        return null;
    }


    private String iterableToSpitStr(Iterable<PK> pks, String separator) {
        StringBuilder s = new StringBuilder();
        pks.forEach(pk -> s.append(pk).append(separator));

        if (StringUtils.isEmpty(s.toString())) {
            return null;
        } else {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
}
