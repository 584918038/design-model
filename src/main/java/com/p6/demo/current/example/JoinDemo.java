package com.p6.demo.current.example;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/21
 */
public class JoinDemo {

    private static int i = 10;


    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(()->{

            i = 30;
        });

        t.start();
        // happens-before 模型
        t.join();
//        Thread.sleep(3000);

        System.out.println("i : " + i);
    }

}
