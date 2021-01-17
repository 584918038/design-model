package com.p6.demo.design_model.adapter.general.demo.classadapter;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/12/21
 */
public class PowerAdapter extends AC220 implements DC5{


    @Override
    public int output5V() {

        int adapterInput = super.outputAC220V();
        int adapterOutput = adapterInput / 44;
        System.out.println("使用Adapter输入AC" + adapterInput + "V,输出DC" + adapterOutput + "V");
        return 0;
    }
}
