package com.p6.demo.trasaction.controller;


import com.p6.demo.trasaction.domain.TCity;
import com.p6.demo.trasaction.domain.TLevel;
import com.p6.demo.trasaction.service.ITCityService;
import com.p6.demo.trasaction.service.ITLevelService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 城市表 前端控制器
 * </p>
 *
 * @author xshlxx@126.com
 * @since 2021-01-12
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TCityController {

    @Autowired
    private ITCityService cityService;

    @Autowired
    private ITLevelService levelService;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    @GetMapping("/transaction")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testTransactionFirst() {

        // 我们演示下事务要么全部成功，要么全部失败, 将异常放置到最后

        // city： 1 沈阳 2 大连
        TCity cityInfo = cityService.getById(1);
        cityInfo.setCity("我要回家!");

        cityService.updateById(cityInfo);
        log.info("城市信息:{}", cityInfo);

        // level: 1 铜牌 2 银牌会员 3 金牌会员
        TLevel levelInfo = levelService.getById(1);
        log.info("等级信息:{}", levelInfo);

//        throw new IllegalArgumentException("测试抛出异常");
    }

    @GetMapping("/transaction/mid")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testTransactionMid() {

        // 我们演示下事务要么全部成功，要么全部失败, 将异常放置到最后

        // city： 1 沈阳 2 大连
        TCity cityInfo = cityService.getById(1);
        log.info("城市信息:{}", cityInfo);

        // 会抛出异常
        int midValue = 9 / 0;

        // level: 1 铜牌 2 银牌会员 3 金牌会员
        TLevel levelInfo = levelService.getById(1);
        log.info("等级信息:{}", levelInfo);

        // result: 未执行第二个操作,其实上面的读是热身，只能让你有个感觉，下面是重头戏，让我们针对写进行操作
    }

    @GetMapping("/transaction/alter1")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void testTransactionAlterFirst() {

        // 我们演示下事务要么全部成功，要么全部失败, 将异常放置到最后

        // city： 1 沈阳 2 大连
        TCity cityInfo = cityService.getById(1);
        cityInfo.setCity("沈阳中毒了");
        cityService.updateById(cityInfo);
        log.info("数据库应该更正的信息为:{}", cityInfo);

        // 会抛出异常
        int midValue = 9 / 0;

        // level: 1 铜牌 2 银牌会员 3 金牌会员
        TLevel levelInfo = levelService.getById(1);
        log.info("等级信息:{}", levelInfo);

        // 结果当然是数据库没有被更新，因为存在事务java.lang.ArithmeticException是Exception的子类，必背捕获因此必背捕获!
        // 那么抛出两个问题:
        // 1、是如何回滚的
        // 2、在没有提交前我们update所执行的操作到哪里去了？
        //
        // 以上三个案例演示了，事务是一体的，要么全部成功，要么全部失败！
        // 那么存在一种场景请求A涉及到了两个数据库请求例如: 我们redis 定时同步 mysql 数据，采取的方式就是成功的就提交，不成功的移交到队列里面再次尝试
        // 这里就要说到了我们的 propagation = Propagation.REQUIRES_NEW
        // 简单阐述下:
        //             标志REQUIRES_NEW会新开启事务，外层事务不会影响内部事务的提交/回滚
        //             标志REQUIRES_NEW的内部事务的异常，会影响外部事务的回滚
        // 下面我们来举个案例来具体看看
    }

    @GetMapping("/transaction/alter2")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testTransactionAlterNew() {

        // 我们演示下事务要么全部成功，要么全部失败, 将异常放置到最后

        // city info
        TCity cityInfo = cityService.getById(1);

        // city： 1 沈阳沈阳小沈阳 ---> 沈阳中毒了
        cityService.alterCityInfo(cityInfo);
        log.info("测试New事务");

        cityInfo.setProvinceId(998);
        cityService.updateById(cityInfo);
        log.info("数据库应该更正的信息为:{}", cityInfo);
        // 会抛出异常
        int midValue = 9 / 0;

        // 预测结果为: 沈阳中毒了提交成功！ 998未提交成功！
        // 但是在我做这个这个案例的时候，如果我这么移动，如下:
    }

    @GetMapping("/transaction/alter3")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testTransactionAlterNew3() {

        // 我们演示下事务要么全部成功，要么全部失败, 将异常放置到最后
        // 这个顺序会出现问题，会锁表为什么？

        // city info
        TCity cityInfo = cityService.getById(1);
        cityInfo.setProvinceId(998);
        cityService.updateById(cityInfo);
        log.info("数据库应该更正的信息为:{}", cityInfo);


        // city： 1 沈阳沈阳小沈阳 ---> 沈阳中毒了
        cityService.alterCityInfo(cityInfo);
        log.info("测试New事务");
        // 会抛出异常
//        int midValue = 9/0;

        // 预测结果为: 沈阳中毒了提交成功！ 998未提交成功！
    }

    @GetMapping("/transaction/state")
    public void stateTransaction() {

        // 手动开启事务
        TransactionStatus transactionStatus = null;

        try {
            // 手动开启事务
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

            TCity cityInfo = cityService.getById(1);
            cityInfo.setProvinceId(998);
            cityService.updateById(cityInfo);
            // 下面这行会抛错，如果事务生效那么必然会提交失败
            int x = 9 / 0;
            // 提交事务
            dataSourceTransactionManager.commit(transactionStatus);


            // 在JDBC的基础之上拿到数据库连接connection , 是基于JDBC的

            // 1、 拿到一个connection对象，并且把自动提交设置为false

            // 2、 如果说要提交，拿到当前的connection对象，然后执行connection.commit()

            // 3、 如果说要回滚，拿到当前的connection对象，然后执行connection.rollback()

            // 4、



        } catch (Exception ex) {

            // 事务回滚
            if (transactionStatus != null) {
                log.info("事务回滚--start");
                dataSourceTransactionManager.rollback(transactionStatus);
                log.info("事务回滚--end");
            }

        }
    }

}
