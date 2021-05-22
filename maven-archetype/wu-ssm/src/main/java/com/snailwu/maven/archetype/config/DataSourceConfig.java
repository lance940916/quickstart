package com.snailwu.maven.archetype.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 吴庆龙
 * @date 2020/5/22 1:41 下午
 */
@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

    @Value("${hikari.jdbc-url}")
    private String jdbcUrl;
    @Value("${hikari.driver-class-name}")
    private String driverClassName;
    @Value("${hikari.username}")
    private String username;
    @Value("${hikari.password}")
    private String password;
    @Value("${hikari.connection-timeout}")
    private Long connectionTimeout;
    @Value("${hikari.idle-timeout}")
    private Long idleTimeout;
    @Value("${hikari.maximum-pool-size}")
    private Integer maximumPoolSize;
    @Value("${hikari.max-life-time}")
    private Long maxLifeTime;

    /**
     * 数据库连接池配置
     */
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setMaxLifetime(maxLifeTime);
        return dataSource;
    }

    /**
     * JDBC 事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
