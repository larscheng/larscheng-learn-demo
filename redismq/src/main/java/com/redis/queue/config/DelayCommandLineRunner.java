//
//package com.redis.queue.config;
//
//import com.redis.queue.reidsqueue.Consumer;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * 项目启动时开始创建线程池检测redisMq中的延时任务
// * @author zhengql
// * @date 2018/12/11 16:44
// */
//
//@Component
//public class DelayCommandLineRunner implements CommandLineRunner {
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        CountDownLatch latch = new CountDownLatch(1);
//        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
//        threadPoolExecutor.submit(new Consumer());
//    }
//
//
//}
//
