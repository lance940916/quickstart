package com.snailwu.untitled;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author 吴庆龙
 * @date 2020/5/20 4:54 下午
 */
public class WuClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(83));
        socketChannel.write(ByteBuffer.wrap(
                URLEncoder.encode("你好 Server over", UTF_8.name()).getBytes(UTF_8))
        );
    }
}
