package com.snailwu.demo;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author 吴庆龙
 * @date 2020/5/20 4:27 下午
 */
public class WuServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(83));

        Selector selector = Selector.open();
        //注意、服务器通道只能注册SelectionKey.OP_ACCEPT事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        try {
            while (true) {
                // 如果条件成立，说明本次询问 selector，并没有获取到任何准备好的、感兴趣的事件
                // java 程序对多路复用 IO 的支持也包括了 阻塞模式 和 非阻塞模式 两种。
                if (selector.select(100) == 0) {
                    //================================================
                    //      这里视业务情况，可以做一些然并卵的事情
                    //================================================
                    continue;
                }

                //这里就是本次询问操作系统，所获取到的“所关心的事件”的事件类型(每一个通道都是独立的)
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey sk = keyIterator.next();
                    keyIterator.remove();

                    SelectableChannel selectableChannel = sk.channel();
                    if (sk.isValid() && sk.isAcceptable()) {
                        System.out.println("======channel通道已经准备好=======");
                        /*
                         * 当server socket channel通道已经准备好，就可以从server socket channel中获取socketchannel了
                         * 拿到socket channel后，要做的事情就是马上到selector注册这个socket channel感兴趣的事情。
                         * 否则无法监听到这个socket channel到达的数据
                         **/
                        ServerSocketChannel channel = (ServerSocketChannel) selectableChannel;
                        SocketChannel socketChannel = channel.accept();
                        socketChannel.configureBlocking(false);
                        //socket通道可以且只可以注册三种事件SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(64));
                    } else if (sk.isValid() && sk.isConnectable()) {
                        System.out.println("======socket channel 建立连接=======");
                    } else if (sk.isValid() && sk.isReadable()) {
                        System.out.println("======socket channel 数据准备完成，可以去读==读取=======");
                        readSocketChannel(sk);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }

    }

    private static void readSocketChannel(SelectionKey sk) throws IOException {
        SocketChannel clientSocketChannel = (SocketChannel) sk.channel();

        // 获取客户端使用的端口
        InetSocketAddress sourceSocketAddress = (InetSocketAddress) clientSocketChannel.getRemoteAddress();
        int resoucePort = sourceSocketAddress.getPort();

        // 拿到这个socket channel使用的缓存区，准备读取数据
        ByteBuffer contextBuffer = (ByteBuffer) sk.attachment();
        // 将通道的数据写入到缓存区，注意是写入到缓存区
        int readLength = -1;
        try {
            readLength = clientSocketChannel.read(contextBuffer);
        } catch (Exception e) {
            //这里抛出了异常，一般就是客户端因为某种原因终止了。所以关闭channel就行了
            e.printStackTrace();
            clientSocketChannel.close();
            return;
        }

        // 如果缓存区中没有任何数据(但实际上这个不太可能，否则就不会触发OP_READ事件了)
        if (readLength == -1) {
            System.out.println("====缓存区没有数据? ====");
            clientSocketChannel.close();
            return;
        }

        // 将缓存区从写状态切换为读状态(实际上这个方法是读写模式互切换)。
        contextBuffer.flip();

        byte[] messageBytes = contextBuffer.array();
        String messageEncode = new String(messageBytes, 0, contextBuffer.limit(), UTF_8);
        String message = URLDecoder.decode(messageEncode, UTF_8.name());

        // 如果收到了“over”关键字，才会清空buffer，并回发数据
        // 否则不清空缓存，还要还原buffer的“写状态”
        if (message.contains("over")) {
            // 清空已经读取的缓存，并从新切换为写状态(这里要注意clear()和capacity()两个方法的区别)
            contextBuffer.clear();
            System.out.println("端口: " + resoucePort + ", 客户端发来消息: " + message);

            //======================================================
            //          当然接受完成后，可以在这里正式处理业务了
            //======================================================

            // 回发数据，关闭 channel
            ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("回发处理结果", UTF_8.name()).getBytes(UTF_8));
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        } else {
            System.out.println("端口: " + resoucePort + ", 客户端信息还未接受完，继续接受======message: " + message);
            contextBuffer.position(readLength);
            contextBuffer.limit(contextBuffer.capacity());
        }
    }
}
