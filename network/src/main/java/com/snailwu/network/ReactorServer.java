package com.snailwu.start.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WuQinglong
 * @date 2021/4/15 13:55
 */
public class ReactorServer implements Runnable {

    private final Selector selector = Selector.open();
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

    public ReactorServer() throws IOException {
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (selector.select() <= 0) {
                    continue;
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();

                    // 分发事件
                    dispatchEvent(selectionKey);

                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 分发事件处理
     */
    private void dispatchEvent(SelectionKey selectionKey) throws IOException {
        // 这就是之前注册的 acceptor 对象
        Object attachment = selectionKey.attachment();
        if (attachment != null) {
            ((Acceptor) attachment).run();
        }
    }

    /**
     * 连接事件就绪,处理连接事件
     */
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new Handler(selector, socketChannel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理器
     */
    class Handler implements Runnable {
        private final SocketChannel socketChannel;
        private SelectionKey sk;
        private final int READING = 0, SENDING = 1;
        private int state = READING;

        public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);

            socketChannel.register(selector, 0);

            selector.wakeup();

        }

        @Override
        public void run() {
//            try {
////                // 读取数据
////                byte[] buffer = new byte[1024];
////                int read = socket.getInputStream().read(buffer);
////
////                // 写入数据
////                socket.getOutputStream().write(buffer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

}
