package com.p6.demo.current.example;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/21
 */
public class ThreadLocalDemo {

    // private static int num = 0;

    static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){

        @Override
        protected Integer initialValue() {

            return 0; // 初始化一个值
        }
    };



    public static void main(String[] args) {

        Thread[] thread = new Thread[5];

        for (int i = 0; i < 5; i ++) {

            thread[i] = new Thread(() ->{

                // 获得的值都是0
                int num = local.get();

                // 设置到local中
                local.set(num +=5);

                System.out.println(Thread.currentThread().getName() + "-" + num);
            });
        }

        for (int i = 0; i < 5; i++ ) {

            thread[i].start();
        }
    }
}
