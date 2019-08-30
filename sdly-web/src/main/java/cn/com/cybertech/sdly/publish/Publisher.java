package cn.com.cybertech.sdly.publish;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.EventObject;


@Component
public class Publisher implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;

    }

    public void publish(EventObject eventObject){
        applicationContext.publishEvent(eventObject);
    }
}