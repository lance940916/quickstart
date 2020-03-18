package com.snailwu.website.tools;

import org.flywaydb.core.Flyway;

import java.nio.charset.StandardCharsets;

/**
 * 自动生成数据库表
 *
 * @author: 吴庆龙
 * @date: 2020/3/17 3:23 下午
 */
public class FlywayTools {
    public static void main(String[] args) {
        FlywayTools tools = new FlywayTools();

        // 获取属性
        String url = "jdbc:mariadb://108.160.139.89:3306/website";
        String user = "snailwu";
        String password = "snailwu9595";

        // Sql 文件路径
        String sqlFilePath = "classpath:sql";

        // 创建Flyway
        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .encoding(StandardCharsets.UTF_8)
                .locations(sqlFilePath)
                .load();

        // 清除数据库中的表
//        flyway.clean();

        // 将SQL语句映射到数据库
        flyway.migrate();

    }
}
