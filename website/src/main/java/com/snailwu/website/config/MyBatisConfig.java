package com.snailwu.website.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.sql.SQLException;

/**
 * MyBatis 配置
 *
 * @author: 吴庆龙
 * @date: 2020/3/17 1:58 下午
 */
@Slf4j
@Configuration
@MapperScan("mapper")
public class MyBatisConfig {

    /**
     * 数据库连接池
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("snailwu");
        dataSource.setPassword("snailwu9595");
        dataSource.setUrl("jdbc://mariadb://108.160.139.89:3306/website");
        // 初始化连接数量
        dataSource.setInitialSize(1);
        // 最大连接数量
        dataSource.setMaxActive(3);
        // 最小连接数量
        dataSource.setMinIdle(1);
//        dataSource.setFilters("filter:wall");
//        dataSource.setFilters("filter:log4j");
        return dataSource;
    }

    /**
     * SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 数据库连接池
        factoryBean.setDataSource(dataSource());

        // 配置 Mapper 文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
        factoryBean.setMapperLocations(resources);

        // 其它属性
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setDefaultStatementTimeout(1000 * 5); // SQL 语句执行超时时间
        configuration.setMapUnderscoreToCamelCase(true); // 下划线命名直接转为驼峰命名
//        configuration.setDefaultEnumTypeHandler(); // 设置默认的枚举类处理
        configuration.setLogImpl(Log4j2Impl.class); // 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    /**
     * 事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

}
