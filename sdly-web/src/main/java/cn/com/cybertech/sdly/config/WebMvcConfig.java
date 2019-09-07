package cn.com.cybertech.sdly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by huangkd on 2019/1/26.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new TokenExceptionInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Methods","Access-Control-Allow-Headers")
                .allowedMethods("PUT","POST", "GET","DELETE")
                .allowCredentials(false).maxAge(3600);
    }
}
