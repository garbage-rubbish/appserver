package com.hkd;

import com.hkd.controller.AuthController;
import org.springframework.beans.BeansException;
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
