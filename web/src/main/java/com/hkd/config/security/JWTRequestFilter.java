package com.hkd.config.security;

import com.hkd.constants.Constants;
import com.hkd.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huangkd on 2019/1/25.
 * 拦截请求校验token
 */
public class JWTRequestFilter extends OncePerRequestFilter {


    private UserDetailsService userDetailsService;

    public JWTRequestFilter(UserDetailsService userDetailsService) {
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "180");
        String token=request.getHeader(Constants.TOKEN_HEADER);
        //token = token.replace("Bearer ","");
        if(StringUtils.isEmpty(token)){
            chain.doFilter(request,response);
            return ;
        }

        String username;
        try{
             username=JwtTokenUtil.getUsernameFromToken(token);
        }catch (RuntimeException e){
            //将抛出的token解析异常设置到request中 在后面针对处理
            request.setAttribute(Constants.TOKEN_EXCEPTION_HEADER,e);
            chain.doFilter(request,response);
            return ;
        }

        if(StringUtils.isEmpty(username)){
            chain.doFilter(request,response);
            return;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
            userDetails,userDetails.getPassword(),userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
