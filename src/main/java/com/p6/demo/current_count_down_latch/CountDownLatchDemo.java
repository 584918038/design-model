package com.p6.demo.current_count_down_latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author 徐恩晗 xshlxx@126.com
 * @since 2021/1/18
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "->begin");
            // 初始值-1
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "->end");
        },"t1").start();

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "->begin");
            // 初始值-1
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "->end");
        },"t2").start();

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "->begin");
            // 初始值-1
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "->end");
        },"t3").start();



        // 阻塞main 线程
        countDownLatch.await();
    }
}
