package com.p6.demo.currrent3;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/3
 */
public class Producer implements Runnable {


    private Queue<String> msg;

    private int maxSize;

    Lock lock;

    Condition condition;


    public Producer(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
        this.msg = msg;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        int i = 0;

        while (true) {

            i++;
            System.out.println(Thread.currentThread().getName() + "尝试获取锁！");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取锁成功！");
            while (msg.size() == maxSize) {

                System.out.println("生产者队列满了！");

                try {
                    // 阻塞线程并释放锁
                    System.out.println(Thread.currentThread().getName() + "执行await，阻塞自身！");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "被唤醒，await结束！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "生产消息: " + i);
            msg.add("生产者的消费内容" + i);
            // 唤醒阻塞状态下的线程
            System.out.println(Thread.currentThread().getName() + "执行signal，开始！");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "执行signal，结束！");

            System.out.println(Thread.currentThread().getName() + "尝试释放锁！");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁成功！");
        }
    }
}
