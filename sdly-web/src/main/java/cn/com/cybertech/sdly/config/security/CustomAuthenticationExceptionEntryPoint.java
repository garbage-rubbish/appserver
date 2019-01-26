package cn.com.cybertech.sdly.config.security;

import cn.com.cybertech.sdly.constants.Constants;
import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.result.PlatformResult;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huangkd on 2019/1/25.
 * 未经过认证抛出异常时 通过此类处理
 */
public class CustomAuthenticationExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        Object token_exception = httpServletRequest.getAttribute(Constants.TOKEN_EXCEPTION_HEADER);
        if(token_exception instanceof SignatureException
                || token_exception instanceof UnsupportedJwtException
                || token_exception instanceof MalformedJwtException
                || token_exception instanceof IllegalArgumentException){
            //token 验证异常
            write(httpServletResponse,PlatformResult.failure(ResultCode.PARSE_TOKEN_ERROR));
        }else if(token_exception instanceof ExpiredJwtException){
            //token 过期异常
            write(httpServletResponse,PlatformResult.failure(ResultCode.TOKEN_EXPIRED));
        }else{
            //AccessDeniedException
            write(httpServletResponse,PlatformResult.failure(ResultCode.UN_AUTH_USER));
        }



    }

    private void write(HttpServletResponse response,PlatformResult result) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type","application/json");
        Gson gson=new Gson();
        response.getWriter().write(gson.toJson(result));
    }
}
