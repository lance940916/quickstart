package com.snailwu.start.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WuQinglong
 * @date 2021/4/15 13:40
 */
public class NIOServer implements Runnable {

    private final Selector selector = Selector.open();
    private final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

    public NIOServer() throws IOException {
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
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
        if (selectionKey.isAcceptable()) {
            // 新连接注册
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            // 可读
        } else if (selectionKey.isWritable()) {
            // 可写
        }
    }
}
