package com.p6.demo.trasaction.service;

import com.p6.demo.trasaction.domain.TCity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 城市表 服务类
 * </p>
 *
 * @author xshlxx@126.com
 * @since 2021-01-12
 */
public interface ITCityService extends IService<TCity> {

    /**
     * 为了测试事务
     */
    void alterCityInfo(TCity city);
}
