package com.p6.demo.trasaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 扫地僧 xshlxx@126.com
 * @since 2021/1/11
 */
public class Demo1 {


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void test() {

        // 首先明白一点，@Transactional默认帮我们开启了事务, 这个后面会说

    }

}
