package com.snailwu.maven.archetype;

import com.snailwu.maven.archetype.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author 吴庆龙
 * @date 2020/2/10 12:49 下午
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
                RootConfig.class
        }
)
public class SpringTestTemplate {

    @Resource
    private DataSource dataSource;

    @Test
    public void testDatasource() throws Exception {
        System.out.println(dataSource.getConnection());
    }

}
