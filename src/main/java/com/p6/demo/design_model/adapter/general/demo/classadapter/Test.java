package com.p6.demo.design_model.adapter.general.demo.classadapter;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/21
 */
public class Test {

    public static void main(String[] args) {

        PowerAdapter adapter = new PowerAdapter();

        adapter.output5V();
        adapter.outputAC220V();
    }
}
