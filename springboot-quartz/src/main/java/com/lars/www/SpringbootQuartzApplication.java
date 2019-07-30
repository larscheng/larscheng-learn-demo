package com.lars.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.lars.www.mapper")
@SpringBootApplication
public class SpringbootQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuartzApplication.class, args);
        System.out.println("【【【【【【定时任务分布式节点 - quartz-cluster-node-first 已启动】】】】】】");
    }

}
