package com.snailwu.mybatis;

import com.snailwu.mybatis.dao.MbgTableMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.snailwu.mybatis.dao.MbgTableDynamicSqlSupport.*;
import static com.snailwu.mybatis.dao.MbgTableDynamicSqlSupport.mbgTable;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author 吴庆龙
 * @date 2020/6/9 6:12 下午
 */
public class MyBatisMain {
    private static final Logger log = LoggerFactory.getLogger(MyBatisMain.class);
    public static void main(String[] args) throws InterruptedException {
        // 数据库连接池
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        log.info("数据库配置");

        // MyBatis
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("dev", transactionFactory, dataSource);

        // 配置参考 https://mybatis.org/mybatis-3/zh/configuration.html#settings
        Configuration configuration = new Configuration(environment);
        configuration.setLogImpl(Slf4jImpl.class);
        configuration.addMapper(MbgTableMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        MbgTableMapper mbgTableMapper = sqlSession.getMapper(MbgTableMapper.class);

        // 动态 SQL
        SelectStatementProvider statementProvider =
                select(count())
                .from(mbgTable)
                .where(id, isNotNull())
                .build()
                .render(RenderingStrategies.MYBATIS3);
        System.out.println(statementProvider.getSelectStatement());
        mbgTableMapper.selectMany(statementProvider);

    }
}