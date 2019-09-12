package cn.com.cybertech.sdly;

import cn.com.cybertech.sdly.controller.AuthController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AuthController bean = beanFactory.getBean(AuthController.class);
        BeanDefinition authController = beanFactory.getBeanDefinition("authController");
        System.out.println(bean);
        System.out.println(authController);
    }
}
