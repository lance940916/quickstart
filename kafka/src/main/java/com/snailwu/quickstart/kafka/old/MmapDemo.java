package com.snailwu.quickstart.kafka.old;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 吴庆龙
 * @date 2020/12/17 下午8:47
 */
public class MmapDemo {

    public static void main(String[] args) throws Exception {

        // mmap
        long begin = System.currentTimeMillis();
        int length = 1024 * 1024 * 1024;
        File file = new File("/Users/wu/mmap.dat");
        MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "rw")
                .getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            mappedByteBuffer.put((byte) '0');
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin); // 1174

//        byte[] bytes = new byte[]{0};
//        // 普通写文件
//        long begin = System.currentTimeMillis();
//        int length = 1024 * 1024 * 1024;
//        File file = new File("/Users/wu/mmap.dat");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
////        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
//        for (int i = 0; i < length; i++) {
////            randomAccessFile.writeByte('0');
//            fileOutputStream.write(bytes, 0, 1);
//        }
//        fileOutputStream.flush();
//        fileOutputStream.close();
//        long end = System.currentTimeMillis();
//        System.out.println(end - begin);

//        byte[] bytes = new byte[]{0};
//        ByteBuffer buffer = ByteBuffer.wrap(bytes, 0, 1);
//        long begin = System.currentTimeMillis();
//        int length = 1024 * 1024 * 1024;
//        File file = new File("/Users/wu/mmap.dat");
//        FileChannel channel = new FileOutputStream(file).getChannel();
//        for (int i = 0; i < length; i++) {
//            channel.write(buffer);
//        }
//        channel.close();
//        long end = System.currentTimeMillis();
//        System.out.println(end - begin);

    }

}
