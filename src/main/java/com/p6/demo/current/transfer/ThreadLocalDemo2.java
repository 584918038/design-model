package com.p6.demo.current.transfer;

/**
 * ThreadLocalDemo2
 *
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/28
 */
public class ThreadLocalDemo2 {

    static ThreadLocal<Integer> locals = new ThreadLocal<Integer>(){

        @Override
        protected Integer initialValue() {

            return 0;
        }
    };
    public static void main(String[] args) {

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i ++) {

            threads[i] = new Thread(() ->{

                Integer integer = locals.get();

                locals.set(integer += 5);

                System.out.println("当前线程的Thread的值为:" + Thread.currentThread().getName() + "  " + integer);
            });
        }

        // 2256
        for (int i = 0; i < threads.length; i ++) {

            threads[i].start();
        }
    }
}
