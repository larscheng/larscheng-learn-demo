package com.learn.multithread._2_threadPool;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/17 13:58
 */
public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 正在执行.....");
    }
}
