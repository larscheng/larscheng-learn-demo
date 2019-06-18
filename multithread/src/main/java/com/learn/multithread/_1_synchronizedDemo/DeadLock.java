package com.learn.multithread._1_synchronizedDemo;

/**
 * 描述:
 * 死锁实例
 * @author zhengql
 * @date 2018/12/14 16:41
 */
public class DeadLock {
    private static String A  = "a";
    private static String B  = "b";


    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    private void deadLock(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("aaaaaaaaaaaaaa");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("bbbbbbbbbbb");
                    }
                }
            }
        });

        t1.start();
        t2.start();

    }
}
