package cn.com.cybertech.sdly.config.security;

import cn.com.cybertech.sdly.enums.ResultCode;
import cn.com.cybertech.sdly.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by huangkd on 2019/1/26.
 * 自定义认证
 */
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService jwtUserDetailService;
    public void setJwtUserDetailService(UserDetailsService jwtUserDetailService){
        this.jwtUserDetailService=jwtUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername((String) authentication.getPrincipal());
        if(userDetails==null){
            throw new BusinessException(ResultCode.USERNAME_NOT_EXIST);
        }
        if(!userDetails.isAccountNonExpired()){
            //TODO throw new BusinessException(ResultCode.XXX)
        }
        if(!userDetails.isEnabled()){
            //TODO
        }
        if(!userDetails.isAccountNonExpired()){
            //TODO
        }
        if(StringUtils.equals(userDetails.getPassword(), (CharSequence) authentication.getCredentials())){
            return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
        }else{
            throw new BusinessException(ResultCode.USERNAME_PASSWORD_ERROR);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
