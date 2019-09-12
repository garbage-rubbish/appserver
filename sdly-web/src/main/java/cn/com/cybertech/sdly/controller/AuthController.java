package cn.com.cybertech.sdly.controller;

import cn.com.cybertech.sdly.model.other.LoginUser;
import cn.com.cybertech.sdly.publish.LoginEvent;
import cn.com.cybertech.sdly.publish.Publisher;
import cn.com.cybertech.sdly.result.PlatformResult;
import cn.com.cybertech.sdly.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
   private Publisher publisher;

    @PostMapping(value = "/login")
    public PlatformResult<String> login(@Validated @RequestBody LoginUser loginUser){

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
//        System.out.println(multipartFile.getSize());
        return "ok";
    }

    @GetMapping("/test1")
    public PlatformResult<String> test1(){
        return PlatformResult.success("sss");
    }
}

