package cn.com.cybertech.sdly.result;

import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Created by huangkd on 2019/1/23.
 * 异常返回
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult implements Result {

    //http状态码
    private Integer status;

    //http 状态英文信息
    private String error;

    //ResultCode.code
    private int code;

    //ResultCode.message
    private String message;

    //异常
    private String exception;

    //时间戳
    private Date timestamp;

    //错误信息
    private Object errors;

    private String path;


    public static ErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus status,Object errors){
        ErrorResult failure = ErrorResult.failure(resultCode, e, status);
        failure.setErrors(errors);
        return failure;

    }

    public static ErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus status){
        ErrorResult errorResult=new ErrorResult();
        errorResult.setStatus(status.value());
        errorResult.setError(status.getReasonPhrase());
        errorResult.setCode(resultCode.getCode());
        errorResult.setMessage(resultCode.getMessage());
        errorResult.setException(e.getClass().getName());
        errorResult.setTimestamp(new Date());
        return errorResult;
    }




}
