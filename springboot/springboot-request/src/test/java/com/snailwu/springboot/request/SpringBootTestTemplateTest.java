package com.snailwu.springboot.request;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

/**
 * SpringBoot 不同于 Spring，它会帮你自动的构建环境，所以可以直接运行测试方法
 *
 * 也可以使用 maven test 进行测试，运行方式同 Spring 一样
 *
 * @author: 吴庆龙
 * @date: 2020/2/10 3:42 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MainApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {
                "spring.config.location=classpath:/application.yml"
        }
)
@AutoConfigureMockMvc
public class SpringBootTestTemplateTest {

    @Resource
    private MockMvc mockMvc;

}
