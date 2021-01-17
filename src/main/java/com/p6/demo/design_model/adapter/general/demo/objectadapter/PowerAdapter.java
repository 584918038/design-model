package com.p6.demo.design_model.adapter.general.demo.objectadapter;

import com.p6.demo.design_model.adapter.general.demo.classadapter.DC5;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/21
 */
public class PowerAdapter implements DC5{

    private AC220 ac220;


    public PowerAdapter(AC220 ac220) {

        this.ac220 = ac220;
    }

    @Override
    public int output5V() {

        int adapterInput = ac220.outputAC220V();
        int adapterOutput = adapterInput / 44;
        System.out.println("使用Adapter输入AC" + adapterInput + "V,输出DC" + adapterOutput + "V");
        return 0;
    }
}
