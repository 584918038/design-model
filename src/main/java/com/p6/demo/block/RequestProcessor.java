package com.p6.demo.block;

/**
 * @author 徐恩晗 xshlxx@126.com
 * @since 2021/1/14
 */
public interface RequestProcessor {

    void processRequest(Request request);

    void shutdown();
}
