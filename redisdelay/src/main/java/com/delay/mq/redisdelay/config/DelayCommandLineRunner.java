
package com.delay.mq.redisdelay.config;

import com.delay.mq.redisdelay.utils.RedisMQ;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 项目启动时开始创建线程池检测redisMq中的延时任务
 * @author zhengql
 * @date 2018/12/11 16:44
 */

@Component
public class DelayCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RedisMQ redisMQ;


    @Override
    public void run(String... args) throws Exception {



        //手动创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,0L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                new ThreadFactoryBuilder().setNameFormat("redisMq-task-%d").build()
        );
        //线程数目
        int threadCount = 3;
        CountDownLatch latch = new CountDownLatch(1);
        try {
            for (int i = 0; i < threadCount; i++) {
                threadPoolExecutor.submit(new ConsumerMessage());
            }
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class ConsumerMessage implements Runnable {

        @Override
        public void run() {
            try {
                //取出到达执行时间的延时任务
                redisMQ.consumerDelayMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

