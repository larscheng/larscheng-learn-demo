package com.learn.multithread._2_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 * 创建一个固定大小的线程池，但不过每次有新的任务提交到线程池时，就会创建一个线程，直到创建的线程到达线程池允许的最大数量时
 * 若还有新的请求，则进入队列等待
 *
 * @author zhengql
 * @date 2018/12/17 14:04
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.submit(new MyThread());
        }

    }
}
