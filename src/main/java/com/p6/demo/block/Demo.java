package com.p6.demo.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/14
 */
public class Demo {

    // 有界队列 长度MAX_VALUE
    static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();


    public static void main(String[] args) {

        // 如果队列满了则报错
        blockingQueue.add("");

        // 如果添加成功会返回一个状态
        blockingQueue.offer("");

        // 阻塞式的添加数据
//        blockingQueue.put("");

    }
}
