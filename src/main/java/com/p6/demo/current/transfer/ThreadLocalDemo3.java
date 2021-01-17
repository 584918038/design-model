package com.p6.demo.current.transfer;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/28
 */
public class ThreadLocalDemo3 {

    static int value = 0;

    public static void main(String[] args) {

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i ++) {

            threads[i] = new Thread(()-> {

                value += 5;

                System.out.println("当前线程的Thread的值为:" + Thread.currentThread().getName() + "  " + value);
            });
        }

        for (int i = 0; i < threads.length; i ++) {

            threads[i].start();
        }
    }
}
