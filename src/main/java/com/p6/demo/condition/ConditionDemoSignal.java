package com.p6.demo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/6
 */
public class ConditionDemoSignal implements Runnable {

    private Lock lock;

    private Condition condition;

    public ConditionDemoSignal(Lock lock, Condition condition) {

        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        System.out.println("begin---ConditionDemoSignal");

        try {

            lock.lock();
            condition.signal();
            System.out.println("end---ConditionDemoSignal");

        } finally {

            lock.unlock();
        }

    }
}
