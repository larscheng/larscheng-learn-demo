package com.learn.multithread._2_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 * 创建一个单线程的线程池，若唯一线程因为异常结束，会立刻创建一个新的线程来代替他
 *
 * @author zhengql
 * @date 2018/12/17 14:00
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            pool.submit(new MyThread());
        }
        pool.shutdown();
    }


}
