package com.p6.demo.design_model.adapter.general.demo.interfaceadapter;


/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/21
 */
public class Test {

    public static void main(String[] args) {

        DC adapter = new PowerAdapter(new AC220());

        adapter.output5V();
        adapter.output12V();

    }
}
