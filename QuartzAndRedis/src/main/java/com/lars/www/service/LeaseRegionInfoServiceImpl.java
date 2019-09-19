package com.lars.www.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lars.www.entity.LeaseRegionInfo;
import com.lars.www.job.TestAddJob;
import com.lars.www.job.VehicleCheckJob;
import com.lars.www.mapper.LeaseRegionInfoMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Calendar.*;
import static java.util.Calendar.FRIDAY;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhengql
 * @since 2019-07-31
 */
@Service
public class LeaseRegionInfoServiceImpl extends ServiceImpl<LeaseRegionInfoMapper, LeaseRegionInfo> {
    @Autowired
    private Scheduler scheduler;

    public void Job(int id,int no) {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = TestAddJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(TestAddJob.class)
                .withIdentity(name, group)
                .withDescription("压测" + no)
                .usingJobData("param", "Cron压测任务编号" + no)
                .usingJobData("id", String.valueOf(id))
                .build();

        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        //每300ms执行一次
                        .withIntervalInMilliseconds(500)
                        //执行1000次
                        .withRepeatCount(300))
                .build();
        //将触发器与任务绑定到调度器内
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public Integer addDbData() {
        LeaseRegionInfo regionInfo = new LeaseRegionInfo()
                .setRegionName("压测数据")
                .setOperatorId(0);

        this.insert(regionInfo);
        return regionInfo.getId();
    }


    public synchronized  Integer addNum(int id) {
        LeaseRegionInfo leaseRegionInfo = this.selectById(id);
        LeaseRegionInfo regionInfo = new LeaseRegionInfo()
                .setId(id)
                .setOperatorId(leaseRegionInfo.getOperatorId()+1);
        this.updateById(regionInfo);
        return regionInfo.getOperatorId();
    }
}
