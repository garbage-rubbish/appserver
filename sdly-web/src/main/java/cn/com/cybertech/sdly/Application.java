package cn.com.cybertech.sdly;

import cn.com.cybertech.sdly.config.datasource.DynamicDataSourceRegister;
import org.activiti.spring.boot.JpaProcessEngineAutoConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by huangkd on 2019/1/20.
 */
@Import(DynamicDataSourceRegister.class)
@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class, JpaProcessEngineAutoConfiguration.class})
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }



}
