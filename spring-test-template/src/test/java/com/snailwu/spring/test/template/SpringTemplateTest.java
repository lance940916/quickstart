package com.snailwu.spring.test.template;

import com.snailwu.spring.test.template.config.WebConfig;
import com.snailwu.spring.test.template.controller.IndexController;
import com.snailwu.spring.test.template.config.RootConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @author: 吴庆龙
 * @date: 2020/2/10 12:49 下午
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
                RootConfig.class, WebConfig.class
        }
)
@WebAppConfiguration
public class SpringTemplateTest {

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Before
    public void beforeTest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Resource
    private IndexController indexController;

    @Test
    public void testController() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/say");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
