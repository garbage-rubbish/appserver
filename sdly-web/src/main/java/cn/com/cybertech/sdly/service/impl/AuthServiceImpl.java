package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.annotations.ChangeDataSource;
import cn.com.cybertech.sdly.service.AuthService;
import cn.com.cybertech.sdly.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by huangkd on 2019/1/26.
 */
@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("jwtUserDetailServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AuthService authService;
 /*   @Override
    public String login(LoginUser loginUser) {
        TpUser byUsername = userService.findUserByMjjh(loginUser.getUsername());
        List<TpUserRole> userRoles = userRoleService.findUserRoleByUserId(byUsername.getId());
        List<Integer> roleIds= Lists.newArrayList();
        userRoles.forEach(userRole->roleIds.add(userRole.getRoleid()));
        List<TpRole> roles = roleService.findRoleByIds(roleIds);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return JwtTokenUtil.createToken(byUsername,roles);


    }
*/

    @Override
    @ChangeDataSource
    public String login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails==null || !passwordEncoder.matches(userDetails.getPassword(),password)){
            throw new BadCredentialsException("用户名或密码错误！");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return JwtTokenUtil.createToken(userDetails);
    }

    @Override
    public void logout() {

    }
}
