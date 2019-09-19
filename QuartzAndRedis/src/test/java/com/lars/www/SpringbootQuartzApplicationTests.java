package com.lars.www;

import com.lars.www.entity.QuartzEnetity;
import com.lars.www.enums.EnumJobClassType;
import com.lars.www.service.QuartzJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.calendar.WeeklyCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.MonthDay;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.Calendar.*;
import static java.util.Calendar.THURSDAY;
import static java.util.Calendar.WEDNESDAY;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootQuartzApplicationTests {


    @Autowired
    private QuartzJobService quartzJobService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addJobByCron() {
        for (int i =0;i<10 ;i++){
            Map<Object,Object> map = new HashMap<>();
            map.put("phone","1000000"+i);
            QuartzEnetity quartzEnetity = new QuartzEnetity()
                    .setJobUniqueName("6000000"+i)
                    .setJobGroupType(EnumJobClassType.SendMsgJob.getValue())
                    .setCronExpression("0/25 * * * * ?")
                    .setJobTopic("single")
                    .setMap(map);
            if (!quartzJobService.addJobByCron(quartzEnetity).getCode().equals("8001")){
                System.err.println("添加失败");
            }
        }

    }

    @Test
    public void addJobByTimes() {
        Map<Object,Object> map = new HashMap<>();
        map.put("phone","1000000"+new Random().nextInt(1000)+1000);
        QuartzEnetity quartzEnetity = new QuartzEnetity()
                .setJobUniqueName("6000000"+new Random().nextInt(1000)+1000)
                .setJobGroupType(EnumJobClassType.SendMsgJob.getValue())
                .setDelayTimes(10000L)
                .setJobTopic("single")
                .setMap(map);
        if (!quartzJobService.addJobByCron(quartzEnetity).getCode().equals("8001")){
            System.err.println("添加失败");
        }
    }

    @Test
    public void addJobWithSchedule() {



        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(3)
                        .withIntervalInSeconds(30))
                .build();

        DailyTimeIntervalTrigger dailyTimeIntervalTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9,0))
                                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(17,0))
                                .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY))
                .build();

        CalendarIntervalTrigger calendarIntervalTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                                .withIntervalInYears(1))
                .build();


        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
                .build();



        SimpleScheduleBuilder simpleScheduleBuilder =SimpleScheduleBuilder.simpleSchedule()
                .withRepeatCount(3)
                .withIntervalInSeconds(30);

        DailyTimeIntervalScheduleBuilder dailyTimeIntervalScheduleBuilder =
                DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9,0))
                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(17,0))
                .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY);

        CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder =
                CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                        .withIntervalInYears(1);

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?");


        Map<Object,Object> map = new HashMap<>();
        map.put("phone","aa123456");
        QuartzEnetity quartzEnetity = new QuartzEnetity()
                .setJobUniqueName("aa654321"+new Random().nextInt(1000)+1000)
                .setJobGroupType(EnumJobClassType.SendMsgJob.getValue())
                .setJobTopic("single")
//                .setScheduleBuilder(simpleScheduleBuilder)
//                .setScheduleBuilder(dailyTimeIntervalScheduleBuilder)
//                .setScheduleBuilder(calendarIntervalScheduleBuilder)
                .setScheduleBuilder(cronScheduleBuilder)
                .setMap(map);
        if (!quartzJobService.addJobByCron(quartzEnetity).getCode().equals("8001")){
            System.err.println("添加失败");
        }

    }


}
