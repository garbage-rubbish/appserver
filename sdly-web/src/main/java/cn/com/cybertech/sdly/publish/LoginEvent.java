package cn.com.cybertech.sdly.publish;

import cn.com.cybertech.sdly.model.other.LoginUser;
import org.springframework.context.ApplicationEvent;

import java.util.EventObject;

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
