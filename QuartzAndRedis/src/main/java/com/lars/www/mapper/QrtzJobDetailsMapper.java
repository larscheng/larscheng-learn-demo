package com.lars.www.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.lars.www.entity.JobAndTrigger;
import com.lars.www.entity.JobAndTriggerVo;
import com.lars.www.entity.QrtzJobDetails;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author zhengql
 * @since 2019-07-30
 */
public interface QrtzJobDetailsMapper extends BaseMapper<QrtzJobDetails> {
    List<JobAndTrigger> findJobAll(Page<JobAndTrigger> page, JobAndTriggerVo vo);
}