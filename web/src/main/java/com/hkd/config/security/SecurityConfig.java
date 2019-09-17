package com.hkd.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        customAuthenticationProvider.setJwtUserDetailService(userDetailsService,customPasswordEncoder);
        auth.authenticationProvider(customAuthenticationProvider);
        auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint(){
        return new CustomAuthenticationExceptionEntryPoint();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/public/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
//        http.headers()
//                .frameOptions()
//                .sameOrigin()
//                .and()
//                // disable CSRF, http basic, form login
//                .csrf().disable()
//                // 跨域支持
//                .cors().and()
//
//                .httpBasic().disable() //
//                .formLogin().disable()
//
//                // ReST is stateless, no sessions
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;//

//        http.cors().and().csrf().disable();
        //  http.csrf().disable();
       // http.authorizeRequests().antMatchers("/auth/**").anonymous().anyRequest().hasAnyRole();

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //添加security 无权限访问返回403
        http.exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPoint());
        http.authorizeRequests()
                .antMatchers("/auth/**","/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
               .anyRequest().authenticated();
        http.addFilterAfter(new JWTRequestFilter(userDetailsService),UsernamePasswordAuthenticationFilter.class);


    }


}
