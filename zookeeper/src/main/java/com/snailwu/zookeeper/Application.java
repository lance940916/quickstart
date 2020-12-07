package com.snailwu.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 * @author wu
 */
public class Application {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("127.0.0.1", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("Watcher: " + event.getPath());
            }
        });

//        List<String> children = zk.getChildren("/", false);
//        System.out.println(children);
//
//        String result = zk.create("/wu", "hello-world".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.PERSISTENT);
//        System.out.println(result);
//
//        children = zk.getChildren("/", false);
//        System.out.println(children);

//        zkLock(zk);

        cluster(zk);
    }

    private static void cluster(ZooKeeper zk) {
        try {
            zk.create("/cluster", "".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);

            // 父节点必须是永久节点
            for (int i = 0; i < 10; i++) {
                zk.create("/cluster/zk-node", "".getBytes(StandardCharsets.UTF_8),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL_SEQUENTIAL);
            }
            List<String> children = zk.getChildren("/cluster", false);
            System.out.println(children);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void zkLock(ZooKeeper zk) {
        int threadCount = 20;
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                latch.countDown();
                String threadName = Thread.currentThread().getName();
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    String result = zk.create("/lock", "hello-world".getBytes(StandardCharsets.UTF_8),
                            ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            CreateMode.EPHEMERAL);
                    System.out.println("线程:" + threadName + ",创建成功." + result);
                } catch (KeeperException | InterruptedException e) {
                    System.out.println("线程:" + threadName + ",创建失败.");
                }
            }, "线程-"+i);
        }
    }

}
