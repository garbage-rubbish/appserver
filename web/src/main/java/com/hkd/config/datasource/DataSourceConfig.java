package com.hkd.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource(){
        return new DruidDataSource();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid1")
    public DataSource slaveDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public DynamicDataSource dynamicDataSource(DataSource dataSource,DataSource slaveDataSource){
        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        Map<Object,Object> dataSourceMap= Maps.newHashMap();
        dataSourceMap.put("master",dataSource);
        dataSourceMap.put("slaver",slaveDataSource);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }


    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactory sqlSessionFactory(DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
