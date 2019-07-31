package cn.com.cybertech.sdly.service.impl;

import cn.com.cybertech.sdly.model.po.TpUser;
import cn.com.cybertech.sdly.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void findUserByMjjh() {

        TpUser userByMjjh = userService.findUserByMjjh("012345");
        assertNotNull(userByMjjh);
        assertEquals(userByMjjh.getMjxm(),"aaa");
        TpUser tesss = userService.findUserByMjjh("tesss");
        assertNull(tesss);
    }
}