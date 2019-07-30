package com.lars.www.job;

import com.lars.www.config.MyPort;
import com.lars.www.service.LeaseRegionInfoServiceImpl;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/7/26 15:07
 */
public class ReginCheckJob extends QuartzJobBean {
    @Autowired
    private MyPort myPort;
    @Autowired
    private LeaseRegionInfoServiceImpl leaseRegionInfoService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(myPort.getPort() +
                (String) map.get("param") + "任务开始执行....:" + new Date() + " / " + leaseRegionInfoService.queryRegion());
    }
}
