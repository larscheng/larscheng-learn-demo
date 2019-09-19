package com.lars.www.job;

import com.lars.www.service.QrtzJobDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/7/26 15:07
 */
@Slf4j
public class VehicleCheckJob extends QuartzJobBean {

    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        log.info("{},开始执行....",map.get("param"));
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{},执行结果: {}, 执行耗时：{}ms",map.get("param"),qrtzJobDetailsService.query(),System.currentTimeMillis()-start);
    }
}
