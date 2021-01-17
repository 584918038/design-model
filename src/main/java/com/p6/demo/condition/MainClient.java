package com.p6.demo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/6
 */
public class MainClient {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        Thread t1 = new Thread(new ConditionDemoWait(lock,condition));
        Thread t2 = new Thread(new ConditionDemoSignal(lock,condition));

        t1.start();
        t2.start();
    }
}
