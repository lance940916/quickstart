package com.snailwu;

import com.snailwu.rabbitmq.config.RootConfig;
import com.snailwu.rabbitmq.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
                RootConfig.class,
                WebConfig.class
        }
)
@WebAppConfiguration
public class SpringTestTemplate {

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext wac;

    @Resource
    private RabbitAdmin rabbitAdmin;

    @Before
    public void beforeTest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testRabbitAdmin() {
        Exchange exchange = new DirectExchange("spring.exchange", true, false);
        rabbitAdmin.declareExchange(exchange);

        Queue queue = new Queue("spring.queue", true);
        rabbitAdmin.declareQueue(queue);

        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("r_key").noargs());
    }

    @Test
    public void testController() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/index");
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
