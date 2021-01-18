package com.p6.demo.current_block;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/17
 */
public class UserService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    ArrayBlockingQueue<User> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    // 不断消费队列的线程
    public void init() {

        while (true) {

            // 阻塞式
            try {

                User user = arrayBlockingQueue.take();
                System.out.println("发送积分给: " + user);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean register() {

        User user = new User("Mic");
        addUser(user);
        // 发送积分
        try {
            arrayBlockingQueue.put(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void addUser(User user) {

        System.out.println("添加用户: " + user);
    }


    public static void main(String[] args) {

        UserService userService = new UserService();

        userService.register();

        userService.init();

    }
}
