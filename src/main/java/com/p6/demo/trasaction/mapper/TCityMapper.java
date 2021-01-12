package com.p6.demo.trasaction.mapper;

import com.p6.demo.trasaction.domain.TCity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 城市表 Mapper 接口
 * </p>
 *
 * @author xshlxx@126.com
 * @since 2021-01-12
 */
@Mapper
@Repository
public interface TCityMapper extends BaseMapper<TCity> {

}
