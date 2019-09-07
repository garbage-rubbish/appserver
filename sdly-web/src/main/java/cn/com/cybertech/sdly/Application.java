package cn.com.cybertech.sdly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by huangkd on 2019/1/20.
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "cn.com.cybertech.sdly.mapper")
@ServletComponentScan
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/huangkangda/Documents/OpenSource/sdly-app-server/class ");
        SpringApplication.run(Application.class,args);
    }
}
