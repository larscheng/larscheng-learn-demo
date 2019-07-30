package com.lars.www.web;

import com.lars.www.entity.QuartzEnetity;
import com.lars.www.response.Message;
import com.lars.www.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 描述:
 * Quartz控制类
 *
 * @author zhengql
 * @date 2019/7/29 13:52
 */
@RestController
public class QuartzJobController {

    @Autowired
    private QuartzJobService quartzJobService;


    /***
     * 定时任务添加（cron）
     *
     * 必填参数介绍：
     * @params jobName 任务名称: 必须唯一
     * @params jobGroupType 任务类型: 枚举整型传递
     * @params jobTopic     任务topic
     * @params cronExpression 任务cron表达式
     * @return
     */
    @RequestMapping(value = "/addJobByCron", method = RequestMethod.POST)
    public Message addJobByCron(@RequestBody QuartzEnetity quartzEnetity) {
        return quartzJobService.addJobByCron(quartzEnetity);
    }

    /***
     * 延时任务添加
     *
     * 必填参数介绍：
     * @params jobName 任务名称: 必须唯一
     * @params jobGroupType 任务类型: 枚举整型传递
     * @params jobTopic     任务topic
     * @params delayTimes   任务延时时间：单位ms
     * @return
     */
    @RequestMapping(value = "/addJobByTimes", method = RequestMethod.POST)
    public Message addJobByTimes(@RequestBody QuartzEnetity quartzEnetity) {
        return quartzJobService.addJobByTimes(quartzEnetity);
    }


    /***
     *
     * 自定义策略任务添加
     *
     * 必填参数介绍：
     * @params jobName 任务名称: 必须唯一
     * @params jobGroupType 任务类型: 枚举整型传递
     * @params jobTopic     任务topic
     * @params scheduleBuilder 任务调度策略（调用方自行实现）
     * @return
     */
    @RequestMapping(value = "/addJobWithSchedule", method = RequestMethod.GET)
    public Message addJobWithSchedule(@RequestBody QuartzEnetity quartzEnetity) {
        return quartzJobService.addJobWithSchedule(quartzEnetity);
    }


    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryJob", method = RequestMethod.GET)
    public Map<String, Object> jobList(@RequestParam(value = "pageNum") Integer pageNum,
                                       @RequestParam(value = "pageSize") Integer pageSize) {
        return  quartzJobService.jobList(pageNum, pageSize);
    }


    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    public Message pauseJob(@RequestParam(value = "jobName") String jobName,
                            @RequestParam(value = "jobGroupName", required = false) String jobGroupName) {
        return quartzJobService.pauseJob(jobName, jobGroupName);
    }


    @RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
    public Message resumeJob(@RequestParam(value = "jobName") String jobName,
                             @RequestParam(value = "jobGroupName") String jobGroupName) {
        return quartzJobService.resumeJob(jobName, jobGroupName);
    }


    @RequestMapping(value = "/updateJobByCron", method = RequestMethod.POST)
    public Message updateJobByCron(@RequestParam(value = "jobName") String jobName,
                                   @RequestParam(value = "jobGroupName") String jobGroupName,
                                   @RequestParam(value = "cronExpression") String cronExpression) {
        return quartzJobService.updateJobByCron(jobName, jobGroupName, cronExpression);
    }

    @RequestMapping(value = "/delJob", method = RequestMethod.POST)
    public Message delJob(@RequestParam(value = "jobName") String jobName,
                          @RequestParam(value = "jobGroupName") String jobGroupName) {
        return quartzJobService.delJob(jobName, jobGroupName);
    }

}
