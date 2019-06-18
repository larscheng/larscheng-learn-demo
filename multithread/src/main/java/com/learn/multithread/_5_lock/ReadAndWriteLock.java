package com.learn.multithread._5_lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述:读写锁
 *
 * @author zhengql
 * @date 2018/12/17 16:15
 */
public class ReadAndWriteLock {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        MyCount2 myCount = new MyCount2("123456", 500);
        ReadWriteLock myLock = new ReentrantReadWriteLock(false);

        for (int i = 1; i < 10; i++) {
            if (i % 2 > 0) {
                //读操作，
                pool.submit(new User("zql"+i, myCount, new Random().nextInt(500), myLock,false));

            } else {
                //写操作
                pool.submit(new User("zql"+i, myCount, -new Random().nextInt(500), myLock,true));
            }
        }
        pool.shutdown();
    }


    static class MyCount2 {
        //账号
        private String cid;
        //余额
        private int cash;

        public MyCount2(String cid, int cash) {
            this.cid = cid;
            this.cash = cash;
        }

        public String getCid() {
            return cid;
        }

        public MyCount2 setCid(String cid) {
            this.cid = cid;
            return this;
        }

        public int getCash() {
            return cash;
        }

        public MyCount2 setCash(int cash) {
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
        private MyCount2 myCount;
        private int ioCash;
        private ReadWriteLock  myLock;
        private boolean flag;

        public User(String name, MyCount2 myCount, int ioCash, ReadWriteLock  myLock, boolean flag) {
            this.name = name;
            this.myCount = myCount;
            this.ioCash = ioCash;
            this.myLock = myLock;
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag){
                //写操作
                //获取锁
                myLock.writeLock().lock();
                //执行现金业务
                System.out.println("写：" +name + "正在操作" + myCount + "账户，金额为" + ioCash + "，当前金额为" + myCount.getCash());
                myCount.setCash(myCount.getCash() + ioCash);
                System.out.println("写：" +name + "操作" + myCount + "账户成功，金额为" + ioCash + "，当前金额为" + myCount.getCash());
                //释放锁，否则别的线程没有机会执行了
                myLock.writeLock().unlock();
            }
            else {
                //读操作
                //获取锁
                myLock.readLock().lock();
                //执行现金业务
                System.out.println("读：" +name + "正在查询" + myCount + "账户，当前金额为" + myCount.getCash());
                //释放锁，否则别的线程没有机会执行了
                myLock.readLock().unlock();
            }
        }
    }
}
