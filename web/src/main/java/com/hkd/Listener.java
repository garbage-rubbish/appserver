package com.hkd;

import com.hkd.model.other.LoginUser;
import com.hkd.publish.LoginEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Listener implements ApplicationListener<LoginEvent> {
    @Override
    public void onApplicationEvent(LoginEvent event) {
        LoginUser loginUser = event.getLoginUser();
        System.out.println(loginUser);
    }
}





