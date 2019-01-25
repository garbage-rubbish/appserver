package cn.com.cybertech.sdly.config.security;

import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.result.PlatformResult;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huangkd on 2019/1/25.
 * 处理spring security 403
 */
public class CustomHttp403ForbiddenEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-type","application/json");
        Gson gson=new Gson();
        httpServletResponse.getWriter().write(gson.toJson(PlatformResult.failure(ResultCode.UN_AUTH_USER)));
    }
}
