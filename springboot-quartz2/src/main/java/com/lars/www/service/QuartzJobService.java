package com.lars.www.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lars.www.entity.JobAndTrigger;
import com.lars.www.entity.JobAndTriggerVo;
import com.lars.www.entity.QuartzEnetity;
import com.lars.www.enums.EnumJobClassType;
import com.lars.www.mapper.JobAndTriggerMapper;
import com.lars.www.response.Message;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/7/29 14:02
 */

@Service
public class QuartzJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    public Message addJob(String jobClassName, Integer jobGroupType, String jobTopic, String cronExpression) {
        return Message.getSuccess();
    }

    public Message pauseJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
            return Message.getSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.CODE_ERROR, e.getMessage());
        }
    }

    public Message resumeJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
            return Message.getSuccess();
        } catch (Exception e1) {
            e1.printStackTrace();
            return new Message(Message.CODE_ERROR, e1.getMessage());
        }
    }

    public Message updateJobByCron(String jobClassName, String jobGroupName, String cronExpression) {
        Message message = Message.getError();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            return new Message(Message.CODE_SUCCESS, scheduler.rescheduleJob(triggerKey, trigger));
        } catch (Exception e) {
            e.printStackTrace();
            message.setData(e.getMessage());
        }

        return message;
    }

    public Message delJob(String jobClassName, String jobGroupName) {
        Message message = Message.getError();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            if (scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName))) {
                return Message.getSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setData(e.getMessage());
        }
        return message;
    }

    public Map<String, Object> jobList(int pageNum, int pageSiz) {
        Page<JobAndTrigger> page = new Page<>(pageNum, pageSiz);
        List<JobAndTrigger> list = jobAndTriggerMapper.findJobAll(page, new JobAndTriggerVo());
        page.setRecords(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", list);
        map.put("number", page.getTotal());
        return map;
    }

    public Message addJobByCron(QuartzEnetity quartzEnetity) {
        try {
            // 启动调度器
            scheduler.start();

            String jobClassName = EnumJobClassType.toMap().get(quartzEnetity.getJobGroupType());
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity("J-"+quartzEnetity.getJobUniqueName(), "J-"+jobClassName)
                    .usingJobData("topic",quartzEnetity.getJobTopic())
                    .usingJobData(new JobDataMap(quartzEnetity.getMap()))
                    .build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzEnetity.getCronExpression());

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("T-"+quartzEnetity.getJobUniqueName(), "T-"+jobClassName)
                    .withSchedule(scheduleBuilder).build();


            return new Message(Message.CODE_SUCCESS, scheduler.scheduleJob(jobDetail, trigger));
        } catch (Exception e) {
            return new Message(Message.CODE_ERROR, e.getMessage());
        }
    }

    public Message addJobByTimes(QuartzEnetity quartzEnetity) {
        try {
            // 启动调度器
            scheduler.start();

            String jobClassName = EnumJobClassType.toMap().get(quartzEnetity.getJobGroupType());
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(quartzEnetity.getJobUniqueName(), jobClassName)
                    .usingJobData(new JobDataMap(quartzEnetity.getMap()))
                    .usingJobData("topic",quartzEnetity.getJobTopic())
                    .build();
            //延时
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzEnetity.getJobUniqueName(), jobClassName)
                    .startAt(new Date(System.currentTimeMillis()+quartzEnetity.getDelayTimes())).build();

            return new Message(Message.CODE_SUCCESS, scheduler.scheduleJob(jobDetail, trigger));
        } catch (Exception e) {
            return new Message(Message.CODE_ERROR, e.getMessage());
        }
    }

    public Message addJobWithSchedule(QuartzEnetity quartzEnetity) {

        try {
            // 启动调度器
            scheduler.start();

            String jobClassName = EnumJobClassType.toMap().get(quartzEnetity.getJobGroupType());
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(quartzEnetity.getJobUniqueName(), jobClassName)
                    .usingJobData(new JobDataMap(quartzEnetity.getMap()))
                    .usingJobData("topic",quartzEnetity.getJobTopic())
                    .build();

            //调用方自定义策略
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzEnetity.getJobUniqueName(), jobClassName)
                    .withSchedule(quartzEnetity.getScheduleBuilder()).build();


            return new Message(Message.CODE_SUCCESS, scheduler.scheduleJob(jobDetail, trigger));
        } catch (Exception e) {
            return new Message(Message.CODE_ERROR, e.getMessage());
        }
    }

    public static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job) class1.newInstance();
    }

}
