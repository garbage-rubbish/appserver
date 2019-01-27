package cn.com.cybertech.sdly.model.vo;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import cn.com.cybertech.sdly.model.Model;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangkd on 2019/1/19.
 */
@ApiModel("分页对象")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> implements Model {


    private static final long serialVersionUID = 5928213221742927220L;

    @ApiModelProperty(value = "当前页号")
    private Integer pageNum;

    @ApiModelProperty(value = "每页的数量")
    private Integer pageSize;

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "总页数")
    private Integer pages;

    @ApiModelProperty(value = "结果集")
    private List<T> list;


    /**
     * 应用场景：数据库查出的数据直接转化vo返回
     * @param page pageHelper 分页数据
     * @param voClass
     * @param <T> VO
     * @param <E> PO
     * @return pageVO
     */
    public static <T, E> PageVo<T> build(Page<E> page, Class<T> voClass) {

        PageVo<T> pageVo = new PageVo<>();
        BeanUtils.copyProperties(page, pageVo, "list");
        ArrayList<T> resultVo = Lists.newArrayList();
        List<E> resultPo = page.getResult();
        try {
            for (E e : resultPo) {
                T vo = voClass.newInstance();
                BeanUtils.copyProperties(e, vo);
                resultVo.add(vo);
            }
            pageVo.setList(resultVo);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return pageVo;
    }


    /**
     *
     * @param pagePo
     * @param voList
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T,E> PageVo<T> build(Page<E> pagePo,List<T> voList){
        PageVo<T> pageVo=new PageVo<>();
        BeanUtils.copyProperties(pagePo,pageVo,"list");
        pageVo.setList(voList==null?Lists.newArrayList():voList);
        return pageVo;
    }

    public static int getPages(long total, int pageSize) {
        if (total == 0 || pageSize == 0) {
            return 0;
        }
        return (int) (total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1));
    }

    public int getPages(){
        return getPages(this.total, this.pageSize);
    }

}
