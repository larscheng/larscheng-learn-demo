package com.learn.multithread._2_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 描述:创建一个大小无限但是可以周期性执行任务
 *
 * @author zhengql
 * @date 2018/12/17 14:12
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 1; i < 10; i++) {
            if (i%2>0){
                //延迟30s执行
                ((ScheduledExecutorService) pool).schedule(new MyThread(),30,TimeUnit.SECONDS);
            }else {
                //延迟5s后每5s周期执行
                ((ScheduledExecutorService) pool).scheduleAtFixedRate(new MyScheduledThread(),5,5,TimeUnit.SECONDS);
            }
        }

    }
}
