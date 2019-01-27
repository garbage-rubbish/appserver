package cn.com.cybertech.sdly.model.other;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by huangkd on 2019/1/23.
 * 无效参数项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParameterInvalidItem {

    //错误字段
    private String filed;
    //错误信息
    private String message;

}
