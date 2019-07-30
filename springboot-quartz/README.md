### SpringBoot2.x+Quartz 


### sql 说明

- QRTZ_CALENDARS 以 Blob 类型存储 Quartz 的 Calendar 信息   
- QRTZ_CRON_TRIGGERS 存储 Cron Trigger，包括Cron表达式和时区信息   
- QRTZ_FIRED_TRIGGERS 存储与已触发的 Trigger 相关的状态信息，以及相联 Job的执行信息QRTZ_PAUSED_TRIGGER_GRPS 存储已暂停的 Trigger组的信息   
- QRTZ_SCHEDULER_STATE 存储少量的有关 Scheduler 的状态信息，和别的Scheduler实例(假如是用于一个集群中)   
- QRTZ_LOCKS 存储程序的悲观锁的信息(假如使用了悲观锁)   
- QRTZ_JOB_DETAILS 存储每一个已配置的 Job 的详细信息   
- QRTZ_JOB_LISTENERS 存储有关已配置的 JobListener的信息   
- QRTZ_SIMPLE_TRIGGERS存储简单的Trigger，包括重复次数，间隔，以及已触的次数   
- QRTZ_BLOG_TRIGGERS Trigger 作为 Blob 类型存储(用于 Quartz 用户用JDBC创建他们自己定制的 Trigger 类型，JobStore并不知道如何存储实例的时候)   
- QRTZ_TRIGGER_LISTENERS 存储已配置的 TriggerListener的信息   
- QRTZ_TRIGGERS 存储已配置的 Trigger 的信息   

    表qrtz_job_details:保存job详细信息,该表需要用户根据实际情况初始化   
    job_name:集群中job的名字,该名字用户自己可以随意定制,无强行要求   
    job_group:集群中job的所属组的名字,该名字用户自己随意定制,无强行要求   
    job_class_name:集群中个notejob实现类的完全包名,quartz就是根据这个路径到classpath找到该job类   
    is_durable:是否持久化,把该属性设置为1，quartz会把job持久化到数据库中   
    job_data:一个blob字段，存放持久化job对象   
      
    表qrtz_triggers: 保存trigger信息   
    trigger_name:trigger的名字,该名字用户自己可以随意定制,无强行要求   
    trigger_group:trigger所属组的名字,该名字用户自己随意定制,无强行要求   
    job_name:qrtz_job_details表job_name的外键   
    job_group:qrtz_job_details表job_group的外键   
    trigger_state:当前trigger状态，设置为ACQUIRED,如果设置为WAITING,则job不会触发   
    trigger_cron:触发器类型,使用cron表达式   
      
    表qrtz_cron_triggers:存储cron表达式表   
    trigger_name:qrtz_triggers表trigger_name的外键   
    trigger_group:qrtz_triggers表trigger_group的外键   
    cron_expression:cron表达式   
       
    表qrtz_scheduler_state:存储集群中note实例信息，quartz会定时读取该表的信息判断集群中每个实例的当前状态   
    instance_name:之前配置文件中org.quartz.scheduler.instanceId配置的名字，就会写入该字段，如果设置为AUTO,quartz会根据物理机名和当前时间产生一个名字   
    last_checkin_time:上次检查时间   
    checkin_interval:检查间隔时间   

https://blog.csdn.net/bicheng4769/article/details/81097305

![](https://img-blog.csdn.net/20180718162248553?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JpY2hlbmc0NzY5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

SimpleScheduleBuilder ：最简单的触发器，表示从某一时刻开始，以一定的时间间隔执行任务。 

属性： 
- repeatInterval 重复间隔。
- repeatCount 重复次数。

立刻开始，以后每一个小时执行一次。
```java
Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1)
                .repeatForever())
                .build();
```
    
DailyTimeIntervalScheduleBuilder 
每一天的某一个时间段内，以一定的时间间隔执行任务，可以指定具体的某一天（星期一、星期二、星期三。。） 

属性： 
- intervalUnit 重复间隔（秒、分钟、小时。。。）。
- daysOfWeek 具体的星期。 默认 周一到周日
- startTimeOfDay 每天开始时间 默认 0.0
- endTimeOfDay 每天结束时间，默认 23.59.59
- repeatCount 重复次数。 默认是-1 不限次数
- interval 每次执行间隔

比如每周一到周四早上9点开始，晚上16点结束，每次执行间隔1 小时。

```java
Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                //加入 scheduler之后立刻执行
                .startNow()
                //定时 ，每个1小时执行一次
                .withSchedule(dailyTimeIntervalSchedule()
                        .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0)) //第天9：00开始
                        .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(16, 0)) //16：00 结束
                        .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY) //周一至周四执行
                        .withIntervalInHours(1) //每间隔1小时执行一次
                        ).build();
```


CalendarIntervalScheduleBuilder 
和SimpleScheduleBuilder类似，都是表示从某一时刻开始，以一定时间间隔执行任务。但是SimpleScheduleBuilder无法指定一些特殊情况，比如每个月执行一次，每周执行一次、每一年执行一次 
属性： 
- interval 执行间隔
- intervalUnit 执行间隔的单位（秒，分钟，小时，天，月，年，星期）

```java
  Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                //加入 scheduler之后立刻执行
                .startNow()
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInWeeks(1) //每周执行一次
                        ).build();
```


优先级（Priority）
Misfire(错失触发）策略