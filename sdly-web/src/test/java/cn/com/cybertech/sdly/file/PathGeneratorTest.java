package cn.com.cybertech.sdly.file;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.StringJoiner;

import static org.junit.Assert.*;
public class PathGeneratorTest {

    private PathGenerator pathGenerator;

    private CloseableHttpClient closeableHttpClient;

    @Before
    public void before(){
        pathGenerator=new PathGenerator();
        closeableHttpClient=HttpClients.createDefault();
    }

    @Test
    public void testLogE(){
        System.out.println(Math.pow(2,2));
        System.out.println(Math.sqrt(100));
        System.out.println(Math.log(10));
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
    //gaosi suanfa 1+2+3+....+100  (1+n)*n/2
    public void test1(){
        //System.out.println(new Date().getTime());
        int n=99;
        System.out.println((1+n)*n/2);
    }

    /**
     * httpclient 提交文件
     * @throws IOException
     */
    @Test
    public void testPostPic() throws IOException {
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
        HttpPost httpPost=new HttpPost("http://localhost:10001/auth/test");
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/huangkangda/Desktop/b.png"));
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("file",bytes, ContentType.APPLICATION_OCTET_STREAM,"b.png");
        httpPost.setEntity(multipartEntityBuilder.build());
        CloseableHttpResponse execute = closeableHttpClient.execute(httpPost);
        String s = EntityUtils.toString(execute.getEntity());
        Assert.assertEquals("error","ok",s);
    }

    @Test
    public void testStringJoiner(){
        StringJoiner joiner=new StringJoiner(",");
        joiner.add("sdfdf");
        joiner.add("skdflksd");
        System.out.println(joiner.toString());
    }

    @Test
    public void testPostJson() throws IOException {
        CloseableHttpClient client= HttpClients.createMinimal();
        HttpPost httpPost=new HttpPost("http://localhost:10001/auth/login");
        HttpEntity httpEntity=new  StringEntity("{\n" +
                "    \"username\": \"012345\",\n" +
                "    \"password\": \"123456\"\n" +
                "    \n" +
                "}",ContentType.APPLICATION_JSON);
        httpPost.setEntity(httpEntity);
        CloseableHttpResponse execute = client.execute(httpPost);
        String s = EntityUtils.toString(execute.getEntity());
        System.out.println(s);
        Assert.assertNotNull(s);
    }

    @Test
    public void testPostForm(){
        HttpPost httpPost=new HttpPost();
    }


}