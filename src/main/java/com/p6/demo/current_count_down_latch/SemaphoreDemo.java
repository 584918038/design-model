package com.p6.demo.current_count_down_latch;

import java.util.concurrent.Semaphore;

/**
 * @author 徐恩晗 xshlxx@126.com
 * @since 2021/1/18
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        // 令牌数量
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {

            new Car(semaphore, i).start();

        }
    }

    static class Car extends Thread {

        Semaphore semaphore;

        int num;

        public Car(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }

        @Override
        public void run() {

            try {

                // 获得令牌（没拿到令牌会阻塞，拿到了就可以往下执行）
                semaphore.acquire();
                System.out.println("第" + num + "线程占用一个令牌");

                Thread.sleep(2000);
                System.out.println("第" + num + "线程释放一个令牌");
                // 释放令牌
                semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
