package cn.com.cybertech.sdly.service.impl;


import cn.com.cybertech.sdly.Application;
import cn.com.cybertech.sdly.service.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RoleServiceTest {


    @Autowired
    private RoleService roleService;

    @Test
    public void testFindRoleByIds(){
        Assert.assertNotNull(roleService.findRoleByIds(null));

    }

}
