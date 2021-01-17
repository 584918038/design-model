package com.p6.demo.current1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/30
 */
public class Demo2 {

    // 容器
    static Map<String, Object> cacheMap = new HashMap<>();

    // 重入读写锁
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 读锁
    static Lock readLock = lock.readLock();

    // 写锁
    static Lock writeLock = lock.writeLock();

    // 重入锁
    static Lock reentrantLock = new ReentrantLock();

    private static Object get(String key) {

        try {
            // 获取读锁
            readLock.lock();
            return cacheMap.get(key);

        } finally {

            readLock.unlock();
        }
    }

    private static void write(String key, String value) {


        // 获取写锁
        writeLock.lock();

        try{

            cacheMap.put(key,value);
        }finally {

            writeLock.unlock();
        }
    }


    public static void main(String[] args) {


    }
}
