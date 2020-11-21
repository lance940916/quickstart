package com.snailwu.zookeeper;

import org.apache.zookeeper.server.ZooKeeperServer;

/**
 * @author 吴庆龙
 * @date 2020/10/26 12:10 下午
 */
public class ZookeeperServerApplication {
    public static void main(String[] args) {

//        ZooKeeperServer

        for (int i = 0; i <= 32; i++) {
            System.out.println(i + ": " + ((i + 1) & (i -1)));
        }
        System.out.println(16 & 15);

    }
}
