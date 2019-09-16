package com.hkd.config.security;

import com.hkd.enums.ResultCode;
import com.hkd.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by huangkd on 2019/1/26.
 * 自定义认证
 */
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService jwtUserDetailService;
    private PasswordEncoder passwordEncoder;

    public void setJwtUserDetailService(UserDetailsService jwtUserDetailService, PasswordEncoder passwordEncoder) {
        this.jwtUserDetailService = jwtUserDetailService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        assert jwtUserDetailService != null;
        assert passwordEncoder != null;
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername((String) authentication.getPrincipal());
        if (userDetails == null) {
            throw new BusinessException(ResultCode.USERNAME_NOT_EXIST);
        }
        if (!userDetails.isAccountNonExpired()) {
            //TODO throw new BusinessException(ResultCode.XXX)
        }
        if (!userDetails.isEnabled()) {
            //TODO
        }
        if (!userDetails.isAccountNonExpired()) {
            //TODO
        }
        if (!passwordEncoder.matches((CharSequence) authentication.getCredentials(), userDetails.getPassword())) {
            log.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException("");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == UsernamePasswordAuthenticationToken.class;
    }
}
