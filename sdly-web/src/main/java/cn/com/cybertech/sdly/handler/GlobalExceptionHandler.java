package cn.com.cybertech.sdly.handler;

import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import cn.com.cybertech.sdly.helper.ParameterInvalidItemHelper;
import cn.com.cybertech.sdly.model.other.ParameterInvalidItem;
import cn.com.cybertech.sdly.result.PlatformResult;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Created by huangkd on 2019/1/23.
 * 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


   /* *//**
     * 违反约束异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public PlatformResult handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request){
        log.info("handleConstraintViolationException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItems = ParameterInvalidItemHelper.convertCVEToParameterInvalidItem(e);
        return PlatformResult.failure(ResultCode.PARAM_IS_INVALID,parameterInvalidItems);
    }


    /**
     * 处理参数绑定时异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BindException.class)
    public PlatformResult handleBindException(BindException e, HttpServletRequest request) {
        log.info("handleBindException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.converBRToParameterInvalidItem(e.getBindingResult());
        return PlatformResult.failure(ResultCode.PARAM_IS_INVALID, parameterInvalidItemList);
    }

    /**
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public PlatformResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.info("handleMethodArgumentNotValidException start, uri:{}, caused by: ", request.getRequestURI(), e);
        List<ParameterInvalidItem> parameterInvalidItemList = ParameterInvalidItemHelper.converBRToParameterInvalidItem(e.getBindingResult());
        return PlatformResult.failure(ResultCode.PARAM_IS_INVALID, parameterInvalidItemList);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public PlatformResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request){
        log.info("handleMissingServletRequestParameterException start, uri:{}, caused by: ", request.getRequestURI(), e);
        return PlatformResult.failure(ResultCode.PARAM_IS_INVALID);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public PlatformResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,HttpServletRequest request){
        return PlatformResult.failure(ResultCode.NOT_SUPPORT_REQ_METHOD);
    }

    /**
     * 处理业务异常
     * @param request
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public PlatformResult handleBusinessException(BusinessException e, HttpServletRequest request){
        log.info("handleBusinessException start, uri:{}, exception:{}, caused by: {}", request.getRequestURI(), e.getClass(), e.getMessage());
        return PlatformResult.failure(e.getResultCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public PlatformResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e,HttpServletRequest request){
        return PlatformResult.failure(ResultCode.PARAM_IS_INVALID);
    }

    @ExceptionHandler
    public PlatformResult handleSignatureException(SignatureException e,HttpServletRequest request){
        return PlatformResult.failure(ResultCode.PARSE_TOKEN_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public PlatformResult handleException(Exception e,HttpServletRequest request){
        log.error("handleThrowable start, uri:{}, caused by: ", request.getRequestURI(), e);
        return PlatformResult.failure(ResultCode.SYSTEM_INNER_ERROR);
    }

}
