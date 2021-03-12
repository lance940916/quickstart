package com.snailwu.start;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    LinkedList<Integer> list = new LinkedList<>();
    int maxSize = 10;

    Lock lock = new ReentrantLock();

    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();

    public Integer take() throws InterruptedException {
        lock.lock();
        try {
            while (list.isEmpty()) {
                notEmpty.await();
            }
            Integer first = list.getFirst();
            notFull.signal();
            return first;
        } finally {
            lock.unlock();
        }
    }

    public void offer(Integer i) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == maxSize) {
                notFull.await();
            }
            list.addLast(i);
            notEmpty.notify();
        } finally {
            lock.unlock();
        }
    }

}
