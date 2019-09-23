package com.hkd.controller;

import com.hkd.model.other.LoginUser;
import com.hkd.publish.LoginEvent;
import com.hkd.publish.Publisher;
import com.hkd.result.PlatformResult;
import com.hkd.service.AuthService;
import com.hkd.test.Item;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Created by huangkd on 2019/1/25.
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
   private Publisher publisher;

    @PostMapping(value = "/login")
    public PlatformResult<String> login(@Validated @RequestBody LoginUser loginUser){
        String[] beanNamesForType = applicationContext.getBeanNamesForType(AuthenticationEntryPoint.class);
        Stream.of(beanNamesForType).forEach(s -> System.out.println(s));
        log.info("login:{}",loginUser.toString());
        publisher.publish(new LoginEvent(this,loginUser));
        log.info("publish login event");
        return PlatformResult.success(authService.login(loginUser.getUsername(),loginUser.getPassword()));
    }

    @PostMapping(value = "/test")
    public String test(HttpServletRequest request) throws IOException, ServletException {
        request.getParts().forEach(part -> {
            try {
                System.out.println(part.getInputStream().available());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println(request.getInputStream().available());
        return "ok";
    }

    @PostMapping("/test1")
    public PlatformResult<String> test1(@RequestBody @Valid Item item){
        System.out.println(item);
        return PlatformResult.success("sss");
    }
}

