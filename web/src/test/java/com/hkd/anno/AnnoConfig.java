package com.hkd.anno;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Configuration
//@Import({MyImportSelector.class})
@EnableTransactionManagement
public class AnnoConfig {


    @Bean
    public UserService userService(){
        return new UserService();
    }
}


@Data
class UserService implements B,A{

    @Value("#{20}")
    private String a;
    @Value("#{20-10}")
    private Integer b;


}

class MyImportSelector implements ImportSelector{

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{MyImportBeanDefinitionRegistrar.class.getName()};
    }
}

class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyFactoryBean.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        BeanDefinitionHolder beanDefinitionHolder=new BeanDefinitionHolder(beanDefinition,MyFactoryBean.class.getSimpleName());
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder,registry);
    }
}

class MyFactoryBean implements FactoryBean<UserService>{
    @Override

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserService getObject() throws Exception {
        UserService userService=new UserService();
        userService.setA("1");
        userService.setB(22);

        return userService;
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }
}

interface A{

}
 interface B extends A,C{

 }

 interface C{

 }