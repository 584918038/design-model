package com.p6.demo.currrent3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/3
 */
public class App {

    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();

        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();
        int maxSize = 20;

        Producer producer = new Producer(queue,maxSize,lock, condition);

        Consumer consumer = new Consumer(queue,maxSize,lock,condition);

        Thread t1 = new Thread(producer, "Producer");
        Thread t2 = new Thread(consumer, "Consumer");

        t1.start();
        t2.start();
    }
}
