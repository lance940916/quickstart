package com.snailwu.website.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class DataSourceTest {

    @Resource
    private DataSource dataSource;

    @Test
    public void testConnection(){
        System.out.println(dataSource);
    }

}
