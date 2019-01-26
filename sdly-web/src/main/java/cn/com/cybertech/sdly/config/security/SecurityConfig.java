package cn.com.cybertech.sdly.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Created by huangkd on 2019/1/24.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("jwtUserDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder customPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加自定义密码加密
        auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder);
    }

    //将AuthenticationManager 交由spring管理
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint(){
        return new CustomHttp403ForbiddenEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //  http.csrf().disable();
       // http.authorizeRequests().antMatchers("/auth/**").anonymous().anyRequest().hasAnyRole();
        //添加自定义认证
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        customAuthenticationProvider.setJwtUserDetailService(userDetailsService);
        http.authenticationProvider(customAuthenticationProvider);
        //禁用跨域 session
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //添加security 无权限访问返回403
        http.exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPoint());
        http.authorizeRequests()
                .antMatchers("/auth/**","/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
               .anyRequest().authenticated();
        http.addFilter(new JWTRequestFilter(authenticationManager(),userDetailsService));

    }
}
