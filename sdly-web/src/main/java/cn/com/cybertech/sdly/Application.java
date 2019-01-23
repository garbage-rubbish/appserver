package cn.com.cybertech.sdly;

import cn.com.cybertech.sdly.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by huangkd on 2019/1/20.
 */
@Import(DynamicDataSourceRegister.class)
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
