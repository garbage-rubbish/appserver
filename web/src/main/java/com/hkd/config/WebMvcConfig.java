package com.hkd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

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
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = registry.addResourceHandler("/public");
        resourceHandlerRegistration.addResourceLocations("classpath:/public/");
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
