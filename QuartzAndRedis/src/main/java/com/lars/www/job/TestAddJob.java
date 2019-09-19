package com.lars.www.job;

import com.lars.www.service.LeaseRegionInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/7/31 14:53
 */
@Slf4j
@DisallowConcurrentExecution
public class TestAddJob extends QuartzJobBean {

    @Autowired
    private LeaseRegionInfoServiceImpl leaseRegionInfoService;

    @Override
    protected  void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        long start = System.currentTimeMillis();

        Integer id = Integer.parseInt(map.get("id").toString());

        log.info("{},执行结果: {}, 执行耗时：{}ms", map.get("param"), leaseRegionInfoService.addNum(id), System.currentTimeMillis() - start);
    }
}
