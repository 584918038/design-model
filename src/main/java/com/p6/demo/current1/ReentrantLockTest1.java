package com.p6.demo.current1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/30
 */
public class ReentrantLockTest1 {

    static int value = 0;

    volatile

    static Lock lock = new ReentrantLock();

    public static void incr() {

        if (lock.tryLock()) {
            value++;
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {


        Thread[] threads = new Thread[1000];

        for (int i = 0; i < 1000; i++) {

            threads[i] = new Thread(() -> {
                incr();
            });
        }
        // 启动线程
        for (int i = 0; i < 1000; i++) {

            threads[i].start();
        }

        Thread.sleep(10000);
        System.out.println("value的值为:" + value);
    }
}
