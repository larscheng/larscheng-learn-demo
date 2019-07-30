package com.lars.www.job;

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
public class ReginAddJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("添加成功后30s开始执行....:"+ new Date());
    }
}
