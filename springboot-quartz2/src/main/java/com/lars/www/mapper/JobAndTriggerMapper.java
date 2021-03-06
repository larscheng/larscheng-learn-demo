package com.lars.www.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lars.www.entity.JobAndTrigger;
import com.lars.www.entity.JobAndTriggerVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface JobAndTriggerMapper extends BaseMapper<JobAndTrigger> {
	public List<JobAndTrigger> getJobAndTriggerDetails();

    List<JobAndTrigger> findJobAll(Page<JobAndTrigger> page, JobAndTriggerVo vo);
}
