package com.learn.multithread._5_lock;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:lock锁demo,普通锁
 *
 * @author zhengql
 * @date 2018/12/17 16:02
 */
public class GeneralLock {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        MyCount myCount = new MyCount("123456", 500);
        Lock myLock = new ReentrantLock();
        for (int i = 1; i < 10; i++) {
            if (i % 2 > 0) {
                pool.submit(new User("zql"+i, myCount, new Random().nextInt(500), myLock));

            } else {
                pool.submit(new User("zql"+i, myCount, -new Random().nextInt(500), myLock));
            }
        }
        pool.shutdown();
    }


    static class MyCount {
        //账号
        private String cid;
        //余额
        private int cash;

        public MyCount(String cid, int cash) {
            this.cid = cid;
            this.cash = cash;
        }

        public String getCid() {
            return cid;
        }

        public MyCount setCid(String cid) {
            this.cid = cid;
            return this;
        }

        public int getCash() {
            return cash;
        }

        public MyCount setCash(int cash) {
            this.cash = cash;
            return this;
        }

        @Override
        public String toString() {
            return "MyCount{" +
                    "cid='" + cid + '\'' +
                    ", cash=" + cash +
                    '}';
        }
    }


    static class User implements Runnable {

        private String name;
        private MyCount myCount;
        private int ioCash;
        private Lock myLock;

        public User(String name, MyCount myCount, int ioCash, Lock myLock) {
            this.name = name;
            this.myCount = myCount;
            this.ioCash = ioCash;
            this.myLock = myLock;
        }

        @Override
        public void run() {
            //获取锁
            myLock.lock();
            //执行现金业务
            System.out.println(name + "正在操作" + myCount + "账户，金额为" + ioCash + "，当前金额为" + myCount.getCash());
            myCount.setCash(myCount.getCash() + ioCash);
            System.out.println(name + "操作" + myCount + "账户成功，金额为" + ioCash + "，当前金额为" + myCount.getCash());
            //释放锁，否则别的线程没有机会执行了
            myLock.unlock();
        }
    }
}
