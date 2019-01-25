package cn.com.cybertech.sdly.exceptions;

import cn.com.cybertech.sdly.enums.ResultCode;
import lombok.Data;

/**
 * Created by huangkd on 2019/1/23.
 * 业务异常
 *
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2868852801886705332L;
    private ResultCode resultCode;

    private Object data;

    public BusinessException(ResultCode resultCode,Object data) {
        this.resultCode=resultCode;
        this.data=data;
    }

    public BusinessException(ResultCode resultCode){
        this.resultCode=resultCode;
    }

}
