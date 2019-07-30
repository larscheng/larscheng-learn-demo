package com.lars.www.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lars.www.entity.LeaseRegionInfo;
import com.lars.www.job.ReginAddJob;
import com.lars.www.job.ReginCheckJob;
import com.lars.www.mapper.LeaseRegionInfoMapper;
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
 * @since 2019-07-26
 */
@Service
public class LeaseRegionInfoServiceImpl extends ServiceImpl<LeaseRegionInfoMapper, LeaseRegionInfo> {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private LeaseRegionInfoMapper leaseRegionInfoMapper;

    public Integer saveRegion(LeaseRegionInfo cn) throws SchedulerException {
        leaseRegionInfoMapper.insert(cn);
        addTimer();
        checkTimer();
        return cn.getId();
    }

    private void addTimer() throws SchedulerException {
        //设置开始时间为1分钟后
        int a = new Random().nextInt(1)+2;
        System.out.println(a+"/"+ LocalDate.now());
        long startAtTime = System.currentTimeMillis() + 1000 * 60 * a;
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = ReginAddJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(ReginAddJob.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private void checkTimer() throws SchedulerException {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = ReginCheckJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(ReginCheckJob.class)
                .withIdentity(name, group)
                .withDescription("检查库存00000000000"+new Random().nextInt(10)+25)
                .usingJobData("param","检查库存00000000000"+new Random().nextInt(10)+20)
                .build();
        //设置cron表达式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/20 * * * * ?");
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(scheduleBuilder)
                .build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public Integer queryRegion(){
        return this.selectList(new EntityWrapper<>(new LeaseRegionInfo())).size();

    }


    public void single(String phone){
        System.out.println(new Date()+"向指定用户发短信：" + phone);
    }
    public void all(String msg){
        System.out.println("向所有用户发短信：" + msg);
    }

}
