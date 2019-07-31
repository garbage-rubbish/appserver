package cn.com.cybertech.sdly.model.qo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * Created by huangkd on 2019/1/19.
 */
@Data
@ApiModel("分页查询对象")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageQo {


    @Range(max = Integer.MAX_VALUE,min = 0)
    private int pageNum;
    @Range(max = Integer.MAX_VALUE,min = 0)
    private int pageSize;

    private String orderBy;



}
