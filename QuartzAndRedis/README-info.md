## SpringBoot1.x+Quartz 项目整体介绍

### 项目说明

本项目的定位是服务并支持业务中的各种延时任务、定时任务、复杂重复性任务等

项目基于SpringBoot1.5.9搭建、集成Quartz2.3.0,其中Quartz采用独立数据源进行持久化，数据库采用mysql

注：本项目可支持集群部署

### sql 说明
基于JDBC进行Quartz的持久化，需要进行预先的建表操作，这里对其中各个数据表的内容进行说明

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


### quartz.properties

quartz的配置文件,其中涵盖了quartz搭建中所有需要用到的配置属性，各属性的含义可参考项目中的`quartz.properties`注释


### 三要素

1. Job&JobDetail: 定义任务具体执行的逻辑。
1. Trigger: 触发器，定义任务执行的方式、间隔。
1. Scheduler: 任务调度器，所有的任务都是从这里开始


### Job&JobDetail

JobDetail 就是对Job的定义，而Job是具体执行的逻辑内容。 

> 这里为什么需要有个JobDetail来作为Job的定义，为什么不直接使用Job？ 
解释：如果使用JobDetail来定义，那么每次调度都会创建一个new job实例，这样带来的好处就是任务并发执行的时候，互不干扰，不会对临界资源造成影响。

### Trigger介绍

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

### Scheduler

Scheduler就是Quartz的大脑，所有任务都是由它来设施。

Schduelr包含一个两个重要组件: JobStore和ThreadPool。

JobStore是会来存储运行时信息的，包括Trigger,Schduler,JobDetail，业务锁等。它有多种实现RAMJob(内存实现)，JobStoreTX(JDBC，事务由Quartz管理），JobStoreCMT(JDBC，使用容器事务)，ClusteredJobStore(集群实现)。

ThreadPool就是线程池，Quartz有自己的线程池实现。所有任务的都会由线程池执行。

### 其他属性

优先级（Priority）：

当scheduler比较繁忙的时候，可能在同一个时刻，有多个Trigger被触发了，但资源不足时，会导致影响效率。
此时设置优先级。根据优先级高的先执行。
注:优先级只有在同一时刻执行的Trigger之间才会起作用，如果一个Trigger是9:00，另一个Trigger是9:30。那么无论后一个优先级多高，前一个都是先执行。

优先级的值默认是5，当为负数时使用默认值。最大值似乎没有指定，但建议遵循Java的标准，使用1-10


Misfire(错失触发）策略

类似的Scheduler资源不足的时候、机器崩溃重启、手动暂停Trigger时等，有可能某一些Trigger在应该触发的时间点没有被触发，也就是Miss Fire了。这个时候Trigger需要一个策略来处理这种情况。

这里有个需要重点注意：
>MisFire的触发是有一个阀值，这个阀值是配置在JobStore的。比RAMJobStore是org.quartz.jobStore.misfireThreshold。只有超过这个阀值，才会算MisFire。
小于这个阀值，Quartz是会全部重新触发。

Misfire(错失触发）策略可以在创建Trigger时进行配置

CronTrigger、CalendarIntervalTrigger、DailyTimeIntervalTrigger可配置以下三种策略

    CronScheduleBuilder csb = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
    csb.withMisfireHandlingInstructionDoNothing();
    csb.withMisfireHandlingInstructionFireAndProceed();(默认)
    csb.withMisfireHandlingInstructionIgnoreMisfires();

- withMisfireHandlingInstructionDoNothing：不触发立即执行，等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
- withMisfireHandlingInstructionFireAndProceed：以当前时间为触发频率立刻触发一次执行，然后按照Cron频率依次执行
- withMisfireHandlingInstructionIgnoreMisfires：以错过的第一个频率时间立刻开始执行，重做错过的所有频率周期后，当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行


SimpleTrigger 可配置下六种
    SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();
    ssb.withMisfireHandlingInstructionFireNow();
    ssb.withMisfireHandlingInstructionIgnoreMisfires();
    ssb.withMisfireHandlingInstructionNextWithExistingCount();
    ssb.withMisfireHandlingInstructionNextWithRemainingCount();
    ssb.withMisfireHandlingInstructionNowWithExistingCount();　　（默认）
    ssb.withMisfireHandlingInstructionNowWithRemainingCount();
    
详细的MisFire请参考[这里](https://www.cnblogs.com/skyLogin/p/6927629.html)