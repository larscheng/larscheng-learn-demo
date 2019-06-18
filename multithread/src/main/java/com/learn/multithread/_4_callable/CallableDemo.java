package com.learn.multithread._4_callable;

import java.util.concurrent.*;

/**
 * 描述:
 * 有返回值的线程
 *
 * @author zhengql
 * @date 2018/12/17 15:44
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0 ;i<10;i++){
            Future future  = pool.submit(new MyDemo("线程"+i));
            System.out.println(future.get().toString());
        }
        pool.shutdown();
    }

    static class MyDemo implements Callable{

        private String obj;

        public MyDemo(String obj) {
            this.obj = obj;
        }

        @Override
        public Object call() throws Exception {
            return obj+"返回";
        }
    }
}
