package com.snailwu.untitled;

import java.io.File;
import java.io.IOException;

/**
 * @author 吴庆龙
 * @date 2020/8/20 5:07 下午
 */
public class ConfigMain {

    public static void main(String[] args) throws IOException {

        // 蓝队 A

        File file = new File("/etc/ssh/ssh_config");

//        String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
//        System.out.println(fileContent);

        File dir = file.getParentFile();
        System.out.println(dir.canWrite());

        System.out.println(dir.setWritable(true));

//        FileUtils.copyFile(file, new File(file.getAbsolutePath() + ".bak"));

//        List<String> appendLines = new ArrayList<>();
//        appendLines.add("# Hello World");
//        appendLines.add("# Hello SSH");
//        FileUtils.writeLines(file, appendLines, true);


    }

}
