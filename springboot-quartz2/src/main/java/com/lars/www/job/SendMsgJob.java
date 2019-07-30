package com.lars.www.job;

import com.lars.www.service.LeaseRegionInfoServiceImpl;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 描述:
 * 发短信任务
 *
 * @author zhengql
 * @date 2019/7/29 17:10
 */
public class SendMsgJob extends QuartzJobBean {

    @Autowired
    private LeaseRegionInfoServiceImpl leaseRegionInfoService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        String topic = map.get("topic").toString();
        switch (topic) {
            case "single":
                String phone = map.getString("phone");
                leaseRegionInfoService.single(phone);
                break;
            case "all":
                leaseRegionInfoService.all(map.getString("msg"));
                break;
            default:
                System.out.println("无效的定时任务");
                break;
        }


    }
}
