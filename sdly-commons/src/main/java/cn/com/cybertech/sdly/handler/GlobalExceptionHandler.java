package cn.com.cybertech.sdly.handler;

import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import cn.com.cybertech.sdly.helper.ParameterInvalidItemHelper;
import cn.com.cybertech.sdly.model.other.ParameterInvalidItem;
import cn.com.cybertech.sdly.result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by huangkd on 2019/1/23.
 * 统一异常处理
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 违反约束异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResult handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request){
        log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItems = ParameterInvalidItemHelper.convertCVEToParameterInvalidItem(e);
        return ErrorResult.failure(ResultCode.PARAM_IS_INVALID,e,HttpStatus.BAD_REQUEST,parameterInvalidItems);
    }


    /**
     * 处理参数绑定时异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ErrorResult handleBindException(BindException e, HttpServletRequest request) {
        log.info("handleBindException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.converBRToParameterInvalidItem(e.getBindingResult());
        return ErrorResult.failure(ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);
    }

    /**
     *   处理使用@Validated注解时，参数验证错误异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.info("handleMethodArgumentNotValidException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.converBRToParameterInvalidItem(e.getBindingResult());
        return ErrorResult.failure(ResultCode.PARAM_IS_INVALID, e, HttpStatus.BAD_REQUEST, parameterInvalidItemList);
    }


    /**
     * 处理业务异常
     * @param request
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorResult handleBusinessException(BusinessException e,HttpServletRequest request){
        log.info("handleBusinessException start, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
        //return ErrorResult.failure(e.getResultCode(),e);
        //TODO
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResult handleException(Exception e,HttpServletRequest request){
        log.error("handleThrowable start, uri:{}, caused by: ", request.getRequestURI(), e);
        return ErrorResult.failure(ResultCode.SYSTEM_INNER_ERROR, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
