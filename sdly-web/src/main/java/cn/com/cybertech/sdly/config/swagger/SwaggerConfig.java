package cn.com.cybertech.sdly.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by huangkd on 2019/1/20.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket demo1ApiDocket() {
        return new Docket(DocumentationType.SWAGGER_12)
                .groupName("zhejiang")
                .apiInfo(new ApiInfoBuilder().title("zhejiang wttc").description("wttc").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.com.cybertech.sdly.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
