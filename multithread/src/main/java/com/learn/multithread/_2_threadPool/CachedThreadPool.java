package com.learn.multithread._2_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 * 创建一个可缓存的线程池，如果线程池的大小超过了线程处理任务所需要的线程，那么就回收部分空闲的线程，此种线程池不对池的大小做限制
 * 可以灵活的回收空闲的线程
 *
 * @author zhengql
 * @date 2018/12/17 14:08
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(new MyThread());
        }
    }
}
