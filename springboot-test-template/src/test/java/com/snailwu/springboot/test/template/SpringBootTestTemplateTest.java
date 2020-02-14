package com.snailwu.springboot.test.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

/**
 * @author: 吴庆龙
 * @date: 2020/2/10 3:42 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MainApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {
                "spring.profiles.active=dev",
                "spring.config.location=classpath:/application.yml,classpath:/application-local.yml"
        }
)
@AutoConfigureMockMvc
public class SpringBootTestTemplateTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testController() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/say");

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
