package com.p6.demo.current_count_down_latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author 徐恩晗 xshlxx@126.com
 * @since 2021/1/18
 */
public class CountDownLatchDemo1 implements Runnable{

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void run() {

        // 阻塞线程
        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i ++) {

            new Thread(new CountDownLatchDemo1()).start();
        }

        // 1~0 使得所有处于await的方法都被唤醒
        countDownLatch.countDown();
    }
}
