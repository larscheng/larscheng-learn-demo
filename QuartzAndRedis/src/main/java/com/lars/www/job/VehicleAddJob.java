package com.lars.www.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/7/26 15:07
 */
@Slf4j
public class VehicleAddJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("添加成功{}min后,simple延时任务开始执行....",jobExecutionContext.getJobDetail().getJobDataMap().get("delay"));
    }
}
