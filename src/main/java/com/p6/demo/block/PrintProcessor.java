package com.p6.demo.block;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author 徐恩晗 xshlxx@126.com
 * @since 2021/1/15
 */
public class PrintProcessor extends Thread implements RequestProcessor {

    RequestProcessor nextProcessor;

    // 存储请求数据
    BlockingQueue<Request> requests = new LinkedBlockingDeque<>();

    volatile boolean finished = false;

    public PrintProcessor(RequestProcessor nextProcessor) {

        this.nextProcessor = nextProcessor;
    }


    @Override
    public void run() {

        while (!finished || Thread.currentThread().isInterrupted()) {

            try {

                // 阻塞式获取请求
                Request request = requests.take();

                System.out.println("Print: " + request);

                // 传递给下一个处理器
                nextProcessor.processRequest(request);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {

        requests.add(request);
    }

    @Override
    public void shutdown() {

        System.out.println("PrintProcessor begin shutdown!");
        finished = true;
        requests.clear();
        nextProcessor.shutdown();
    }
}
