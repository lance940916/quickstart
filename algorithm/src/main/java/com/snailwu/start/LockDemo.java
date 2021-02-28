package com.snailwu.start;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Condition c1 = lock.newCondition();





    }

}
