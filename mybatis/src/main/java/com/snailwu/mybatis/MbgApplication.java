package com.snailwu.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴庆龙
 * @date 2020/6/4 3:31 下午
 */
public class MbgApplication {

    public static void main(String[] args) throws Exception {
        // 配置文件路径
        String userDir = System.getProperty("user.dir");
        String filePath = "/mybatis/src/main/resources/mbg-config.xml";
        File file = new File(userDir, filePath);
        if (!file.exists()) {
            throw new RuntimeException("MyBatisGenerator 配置文件不存在.");
        }

        // 获取 Configuration
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(file);

        // 覆盖
        DefaultShellCallback callback = new DefaultShellCallback(true);

        // 进行生成
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(new GeneratorCallback());
    }

    /**
     * 定义回调，打印执行过程
     */
    private static class GeneratorCallback implements ProgressCallback {
        @Override
        public void introspectionStarted(int totalTasks) {
        }

        @Override
        public void generationStarted(int totalTasks) {
        }

        @Override
        public void saveStarted(int totalTasks) {
        }

        @Override
        public void startTask(String taskName) {
            System.out.println("MBG-" + taskName);
        }

        @Override
        public void done() {
            System.out.println("MBG-Finish.");
        }

        @Override
        public void checkCancel() {
        }
    }

}
