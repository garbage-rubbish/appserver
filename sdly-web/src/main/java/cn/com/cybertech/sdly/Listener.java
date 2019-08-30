package cn.com.cybertech.sdly;

import cn.com.cybertech.sdly.model.other.LoginUser;
import cn.com.cybertech.sdly.publish.LoginEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.util.EventObject;

@Component
public class Listener implements ApplicationListener<LoginEvent> {
    @Override
    public void onApplicationEvent(LoginEvent event) {
        LoginUser loginUser = event.getLoginUser();
        System.out.println(loginUser);
    }
}





