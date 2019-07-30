package com.lars.www.mapper;

import com.lars.www.entity.LeaseRegionInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author zhengql
 * @since 2019-07-26
 */
@Mapper
public interface LeaseRegionInfoMapper extends BaseMapper<LeaseRegionInfo> {

}