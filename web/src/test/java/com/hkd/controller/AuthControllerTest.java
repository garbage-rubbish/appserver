package com.hkd.controller;

import org.hamcrest.collection.IsIn;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

   /* @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void before(){
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
*/
    @Test
    public void testValid() throws Exception {
        String sss = mockMvc.perform(MockMvcRequestBuilders.post("/auth/test1").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": 11111,\n" +
                        "  \"name\": \"huangkangda\",\n" +
                        "  \"prop\": {\n" +
                        "    \"id\": 1111111,\n" +
                        "    \"name\": \"zhangsan\"\n" +
                        "  " +
                        "}\n" +
                        "}")

        ).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("sss"))).andReturn()
                .getRequest().getRequestURL().toString();
        System.out.println(sss);
    }
}
