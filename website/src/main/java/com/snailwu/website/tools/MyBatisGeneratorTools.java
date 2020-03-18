package com.snailwu.website.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过数据库表生成 Mapper 和 mapper.xml
 *
 * @author: 吴庆龙
 * @date: 2020/3/17 3:21 下午
 */
public class MyBatisGeneratorTools {
    public static void main(String[] args) throws Exception {
        // 配置文件路径
        String suffix = "website/src/main/resources/mybatis-generator-config.xml";
        String prefix = System.getProperty("user.dir");
        prefix = StringUtils.appendIfMissing(prefix, "/");
        File configFile = new File(prefix + suffix);

        // 解析 xml 文件为 Configuration
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);

        // 覆盖
        DefaultShellCallback callback = new DefaultShellCallback(true);

        // 进行生成
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(new GeneratorCallback());
    }

    /**
     * 定义回调，打印执行过程
     */
    @Slf4j
    private static class GeneratorCallback implements ProgressCallback {

        @Override
        public void introspectionStarted(int totalTasks) {
            log.info("MBG-introspectionStarted. totalTasks:{}", totalTasks);
        }

        @Override
        public void generationStarted(int totalTasks) {
            log.info("MBG-generationStarted. totalTasks:{}", totalTasks);
        }

        @Override
        public void saveStarted(int totalTasks) {
            log.info("MBG-generationStarted. totalTasks:{}", totalTasks);
        }

        @Override
        public void startTask(String taskName) {
            log.info("MBG-startTask. taskName:{}", taskName);
        }

        @Override
        public void done() {
            log.info("MBG-done");
        }

        @Override
        public void checkCancel() {
//            log.info("MBG-checkCancel");
        }
    }
}
