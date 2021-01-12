package com.p6.demo.trasaction.service.impl;

import com.p6.demo.trasaction.domain.TCity;
import com.p6.demo.trasaction.mapper.TCityMapper;
import com.p6.demo.trasaction.service.ITCityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 *
 * @author xshlxx@126.com
 * @since 2021-01-12
 */
@Service
public class TCityServiceImpl extends ServiceImpl<TCityMapper, TCity> implements ITCityService {

    @Autowired
    private TCityMapper cityMapper;


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void alterCityInfo(TCity city) {

        city.setCity("沈阳解毒");
        cityMapper.updateById(city);

    }
}
