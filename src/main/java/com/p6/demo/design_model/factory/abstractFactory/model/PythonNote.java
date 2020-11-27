package com.p6.demo.design_model.factory.abstractFactory.model;

import com.p6.demo.design_model.factory.abstractFactory.api.INote;

/**
 * @author 扫地僧 xshlxx@126.com
 * @since 2020/11/23
 */
public class PythonNote implements INote {

    @Override
    public void edit() {
        System.out.println("编辑了python笔记");
    }
}
