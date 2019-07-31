package cn.com.cybertech.sdly.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangkd on 2019/1/21.
 * 动态添加数据源
 */
@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    private final static ConfigurationPropertyNameAliases ALIASES= new ConfigurationPropertyNameAliases();

    /**
     * 存储已注册数据源
     */
    public static Map<String, DataSource> dataSources=new HashMap<>();

    private Binder binder;
    /**
     * 数据源配置名称不同 添加别名
     * @param environment
     */
    static {
        ALIASES.addAliases("url", "jdbc-url");
        ALIASES.addAliases("username", "user");
        //ALIASES.addAliases("driverClassName","driver-class-name");
    }



    @Override
    public void setEnvironment(Environment environment) {
        log.info("开始绑定数据源...");
        this.environment=environment;
        binder=Binder.get(environment);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        String masterPrefix="spring.datasource.master";
        //String otherPrefix="spring.datasource.other";
        if(StringUtils.isNotEmpty(environment.getProperty("master.datasource.prefix"))){
            masterPrefix=environment.getProperty("master.datasource.prefix");
        }
//        if(StringUtils.isNotEmpty(environment.getProperty("other.datasource.prefix"))){
//            otherPrefix=environment.getProperty("other.datasource.prefix");
//        }
        //主库
        Map master= binder.bind(masterPrefix,Map.class).get();
//        List<Map> configs=binder.bind(otherPrefix,Bindable.listOf(Map.class)).get();
        String type=environment.getProperty(masterPrefix+".type");
        Class<? extends DataSource> dataSourceType = getDataSourceType(type);

        DataSource defaultDataSource=bind(dataSourceType,master);
//        for (Map config : configs) {
//            Class<? extends DataSource> clazz = getDataSourceType(config.get("type").toString());
//            DataSource datasource = bind(clazz, config);
//            DataSourceContextHolder.dataSourceIds.add(config.get("key").toString());
//            dataSources.put(config.get("key").toString(), datasource);
//            log.info("数据源[{}],注册成功", config.get("key").toString());
//        }
        // bean定义类
        GenericBeanDefinition define = new GenericBeanDefinition();
        // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(DynamicDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv = define.getPropertyValues();
        //添加默认数据源
        mpv.add("defaultTargetDataSource",defaultDataSource);
        DataSourceContextHolder.dataSourceIds.add("master");
        // 添加其他数据源
        mpv.add("targetDataSources", dataSources);
        // 将该bean注册为datasource，不使用springboot自动生成的datasource
        beanDefinitionRegistry.registerBeanDefinition("datasource", define);

        log.info("数据源注册完成,[{}]", DataSourceContextHolder.dataSourceIds.toString());
    }

    private Class<? extends DataSource> getDataSourceType(String className){
        if(StringUtils.isEmpty(className)){
            //springboot 默认数据源
            return HikariDataSource.class;
        }
        try {
            return (Class<? extends DataSource>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("数据源类型错误。未找到对应class",e);
        }
        return HikariDataSource.class;
    }

    private <T extends DataSource> T bind(Class<T> clazz, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(source.withAliases(ALIASES));
        // 通过类型绑定参数并获得实例对象
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get();
    }
}
