package cn.com.cybertech.sdly.result;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by huangkd on 2019/1/26.
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestResult<T>  implements Result {

    private int code;
    private String message;
    private T data;


}
