package com.lars.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootQuartzApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuartzApplication2.class, args);
        System.out.println("【【【【【【定时任务分布式节点 - quartz-cluster-node-two 已启动】】】】】】");
    }

}
