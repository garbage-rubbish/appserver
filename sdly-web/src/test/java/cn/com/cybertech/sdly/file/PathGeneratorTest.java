package cn.com.cybertech.sdly.file;

import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;
import java.util.Date;

import static org.junit.Assert.*;
public class PathGeneratorTest {

    private PathGenerator pathGenerator;

    @Before
    public void before(){
        pathGenerator=new PathGenerator();
    }

    @Test
    public void ymdPath() {
        Assert.assertEquals("/2019/5/28/",pathGenerator.ymdPath());
    }

    @Test
    public void ymPath() {
        Assert.assertEquals("/2019/5/",pathGenerator.ymPath());
    }

    @Test
    public void yPath() {
        Assert.assertEquals("/2019/", pathGenerator.yPath());
    }

    @Test
    public void test (){
        String s="eyJhbGciOiJIUzUxMiJ9.eyJtanhtIjoiYWFhIiwic2Z6aCI6IjEyMzEyMzEyIiwicm9sZXMiOlsiUk9MRV9URVNUIl0sIm1qYm0iOiIxMjMxMjMxMjMiLCJleHAiOjE1NTkwOTg3NzYsInVzZXJuYW1lIjoiMDEyMzQ1In0.mdm6-wgnasFS-Cze1ddbekxqTtYeXs5FGMHN_c-oBdbsgl5ZDLRfPmCrrr8ox-3gfagtyhQcSnnSkAGVg2llYA";
        String ss=new String(Base64.getDecoder().decode(s.split("\\.")[1]));
        System.out.println(ss);
    }

    @Test
    public void test1(){
        //System.out.println(new Date().getTime());
    }
}