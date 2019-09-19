package com.lars.www.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lars.www.entity.QrtzJobDetails;
import com.lars.www.job.VehicleAddJob;
import com.lars.www.job.VehicleCheckJob;
import com.lars.www.mapper.QrtzJobDetailsMapper;
import com.lars.www.response.Message;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhengql
 * @since 2019-07-30
 */
@Service
@Slf4j
public class QrtzJobDetailsService extends ServiceImpl<QrtzJobDetailsMapper, QrtzJobDetails> {

    @Autowired
    private Scheduler scheduler;

    public Message saveVehicle(int i) {
        log.info("Test---->添加新的simple延时任务");
        try {
            addTimer(i);

            checkTimer(i);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return new Message(Message.CODE_SUCCESS, i);
    }

    private void addTimer(int i) throws SchedulerException {
        //设置开始时间为x分钟后
        int a = new Random().nextInt(2) + 1;
        long startAtTime = System.currentTimeMillis() + 1000 * 60 * a;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = VehicleAddJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(VehicleAddJob.class).withIdentity(name, group).usingJobData("delay", String.valueOf(a)).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private void checkTimer(int i) throws SchedulerException {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = VehicleCheckJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(VehicleCheckJob.class)
                .withIdentity(name, group)
                .withDescription("检查库存" + i)
                .usingJobData("param", "Cron任务编号" + i)
                .build();
        //设置cron表达式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?").withMisfireHandlingInstructionIgnoreMisfires();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(scheduleBuilder)
                .build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public Integer query() {
        return new Random().nextInt(1000) + 1000;

    }

    public void single(String phone) {
        long start = System.currentTimeMillis();
        long time = new Random().nextInt(3000) + 1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("topic: single ---->向用户 {} 发送短信 , 执行耗时：{}ms", phone, System.currentTimeMillis() - start);
    }

    public void all(String msg) {
        log.info("topic: all ---->向所有用户发送短信 {}", msg);
    }


}
