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
 * 发短信任务
 *
 * @author zhengql
 * @date 2019/7/29 17:10
 */
@Slf4j
public class SendMsgJob extends QuartzJobBean {

    @Autowired
    private QrtzJobDetailsService jobAndTriggerService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        String topic = map.get("topic").toString();
        switch (topic) {
            case "single":
                String phone = map.getString("phone");
                jobAndTriggerService.single(phone);
                break;
            case "all":
                jobAndTriggerService.all(map.getString("msg"));
                break;
            default:
                System.out.println("无效的定时任务");
                break;
        }


    }
}
