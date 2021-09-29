package com.xiao.helloworld;

import com.xiao.helloworld.controller.HelloController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @Date 2021/09/27
 * @Author KongXiao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
class HelloWorldApplicationTest {

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").param("userName", "test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello,test!")));
    }

}
