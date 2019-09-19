## SpringBoot1.x+Quartz 操作手册

### 项目说明

本项目的定位是服务并支持业务中的各种延时任务、定时任务、复杂重复性任务等

项目基于SpringBoot1.5.9搭建、集成Quartz2.3.0,其中Quartz采用独立数据源进行持久化，数据库采用mysql

注：本项目可支持集群部署


### 类目录说明

```code

├─main
│  ├─java
│  │  └─com
│  │      └─lars
│  │          └─www
│  │              ├─config      配置文件
│  │              ├─entity      实体类
│  │              ├─enums       枚举类
│  │              ├─job         Job文件
│  │              ├─mapper      Mapper
│  │              ├─response    Message
│  │              ├─service     业务类
│  │              └─web         接口类
│  └─resources
│      ├─mapper                 *Mapper.xml
│      ├─sql                    quartz数据库脚本
│      └─static                 管理页面
└─test
    └─java
        └─com
            └─lars
                └─www           测试类
```

### 部署说明

quartz使用独立数据源，数据库采用mysql，所有配置一律在quartz.properties中配置

SpringBoot项目本身的配置文件为application.yml

quartz.properties中数据源相关属性名称要与config包下的DataSourceConfig.java中内容一致

如若集群部署，务必确认以下属性

    # 设置调度器的实例名(instanceName) 和实例ID (instanceId)
    org.quartz.scheduler.instanceName= xxxxxx
    #如果使用集群，instanceId必须唯一，设置成AUTO
    org.quartz.scheduler.instanceId = AUTO
    #是否使用集群（如果项目只部署到 一台服务器，就不用了）
    org.quartz.jobStore.isClustered = true
    
### 添加定时任务

项目对外提供了三种添加方式，关于三中添加方式的单元测试可见 [SpringbootQuartzApplicationTests](./src/test/java/com/lars/www/SpringbootQuartzApplicationTests.java)

- addJobByCron(QuartzEnetity quartzEnetity)
- addJobByTimes(QuartzEnetity quartzEnetity)
- addJobWithSchedule(QuartzEnetity quartzEnetity)

三种新建任务接口文档请见：[api.md](/api.md)


### 压测说明

新建任务定时给数据库中数据+1

1. 单机部署测试
    
    - 任务数：1000个定时任务
    - 执行周期：1000次/20s
    - 执行耗时：10-20s
    - quartz线程池：10
    - misfire值：60s(默认misfire策略)

2. 集群部署测试（两台设备）

    - 任务数：1000个定时任务
    - 执行周期：0/20 * * * * ?
    - 执行耗时：10-20s
    - quartz线程池：10
    - misfire值：60s(默认misfire策略)



