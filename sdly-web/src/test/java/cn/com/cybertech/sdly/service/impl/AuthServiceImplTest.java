package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.model.other.LoginUser;
import cn.com.cybertech.sdly.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceImplTest {

    @Autowired
    private AuthService authService;


    @Test
    public void login() {
        LoginUser loginUser=new LoginUser();
        loginUser.setUsername("012345");
        loginUser.setPassword("test"
        );
        try{
        String login = authService.login(loginUser.getUsername(), loginUser.getPassword());
            fail();
        }catch (BadCredentialsException e){
            assertTrue(e.getMessage().contains("用户名或密码错误"));
        }


    }

    @Test
    public void logout() {
    }
}