package com.snailwu.start.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WuQinglong
 * @date 2021/4/15 13:32
 */
public class BIOServer {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            ServerSocket ss = new ServerSocket(9999);
            while (!Thread.interrupted()) {
                Socket accept = ss.accept();
                // 一个链接对应一个线程进行处理
                executorService.submit(new Handler(accept));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Handler implements Runnable {
        private final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 读取数据
                byte[] buffer = new byte[1024];
                int read = socket.getInputStream().read(buffer);

                // 写入数据
                socket.getOutputStream().write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
