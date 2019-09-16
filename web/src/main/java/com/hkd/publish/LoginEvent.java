package com.hkd.publish;

import com.hkd.model.other.LoginUser;
import org.springframework.context.ApplicationEvent;

public class LoginEvent extends ApplicationEvent {


    LoginUser loginUser;

    public LoginEvent(Object source,LoginUser loginUser){
        super(source);
        this.loginUser=loginUser;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }
}
