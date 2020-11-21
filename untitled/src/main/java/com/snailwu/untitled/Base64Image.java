package com.snailwu.untitled;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * @author 吴庆龙
 * @date 2020/8/12 4:53 下午
 */
public class Base64Image {

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/wu/team_a_default_icon.png";
        BufferedImage bufferedImage = ImageIO.read(new File(filePath));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        System.out.println(baos.size());
        byte[] bytes = Base64.getEncoder().encode(baos.toByteArray());


        byte[] decode = Base64.getDecoder().decode(bytes);
        ByteArrayInputStream bais = new ByteArrayInputStream(decode);


    }

}
