package cn.com.cybertech.sdly.config.security;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.constants.Constants;
import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import cn.com.cybertech.sdly.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huangkd on 2019/1/25.
 * 拦截请求校验token
 */
public class JWTRequestFilter extends BasicAuthenticationFilter {


    private UserDetailsService userDetailsService;

    public JWTRequestFilter(AuthenticationManager authenticationManager,UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token=request.getHeader(Constants.TOKEN_HEADER);
        if(StringUtils.isEmpty(token)){
            chain.doFilter(request,response);
            return ;
        }
        //FIXME 拦截器校验token时 抛出校验异常 全局异常处理无法处理 将返回500
        String username=JwtTokenUtil.getUsernameFromToken(token);

        if(StringUtils.isEmpty(username)){
            chain.doFilter(request,response);
            return;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
            userDetails,userDetails.getPassword(),userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        super.doFilterInternal(request, response, chain);
    }
}
