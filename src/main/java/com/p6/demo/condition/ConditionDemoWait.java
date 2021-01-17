package com.p6.demo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/6
 */
public class ConditionDemoWait implements Runnable {


    private Lock lock;

    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        System.out.println("begin---ConditionDemoWait");

        try {

            lock.lock();
            condition.await();
            System.out.println("end---ConditionDemoWait");
        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            lock.unlock();
        }
    }
}
