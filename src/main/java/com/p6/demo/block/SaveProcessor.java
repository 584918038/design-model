package com.p6.demo.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 徐恩晗 xshlxx@126.com
 * @since 2021/1/15
 */
public class SaveProcessor extends Thread implements RequestProcessor{

    RequestProcessor nextProcessor;

    // 存储请求数据
    BlockingQueue requests = new LinkedBlockingQueue();

    volatile boolean finished = false;

    public SaveProcessor(RequestProcessor nextProcessor) {

        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {

        while (!finished) {

            try {
                Request take = (Request) requests.take();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {

        // 添加到阻塞队列
        requests.add(request);
    }

    @Override
    public void shutdown() {

        System.out.println("SaveProcessor begin shutdown!");
        finished = true;
        requests.clear();
        nextProcessor.shutdown();
    }
}
