package com.p6.demo.current1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/28
 */
public class Demo {

    static Map<String, Object> cacheMap = new HashMap<>();

    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    static Lock read = rwl.readLock();

    static Lock write = rwl.writeLock();

    static Lock fromLock = new ReentrantLock();


    public static Object get(String key) {


        if (fromLock.tryLock())
            // 读锁  阻塞
            read.lock();

        try {

            return cacheMap.get(key);

        } finally {

            read.unlock();
        }
    }

    public static Object write(String key, Object value) {

        // other thread  获得了写锁
        write.lock();

        try {
            return cacheMap.put(key, value);
        } finally {
            write.unlock();
        }
    }
}
