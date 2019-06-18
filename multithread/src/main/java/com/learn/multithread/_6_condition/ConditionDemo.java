package com.learn.multithread._6_condition;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

/**
 * 描述:有一个帐户，有多个用户在同时操作这个帐户，有的存款、有的取款，存款无限制，取款不可以透支，任何试图透支的操作，
 * 都必须先等待到账户中有足够的存款才可以继续执行
 *
 * @author zhengql
 * @date 2018/12/17 18:57
 */
public class ConditionDemo {


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        MyselfCount myselfCount = new MyselfCount("66666",1000);

        for (int i = 1; i < 100; i++) {
            if (i % 2 > 0) {
                //读操作，
                pool.submit(new SaveMan(myselfCount,"zql"+i,new Random().nextInt(1000)));

            } else {
                //写操作
                pool.submit(new DrawMan(myselfCount,"zql"+i,new Random().nextInt(1000)));
            }
        }
        pool.shutdown();
    }


    static class SaveMan implements Runnable{
        private MyselfCount myselfCount;
        private String name;
        private int x;

        public SaveMan(MyselfCount myselfCount, String name, int x) {
            this.myselfCount = myselfCount;
            this.name = name;
            this.x = x;
        }

        @Override
        public void run() {
            myselfCount.save(x,name);
        }
    }
    static class DrawMan implements Runnable{
        private MyselfCount myselfCount;
        private String name;
        private int x;

        public DrawMan(MyselfCount myselfCount, String name, int x) {
            this.myselfCount = myselfCount;
            this.name = name;
            this.x = x;
        }

        @Override
        public void run() {
            myselfCount.draw(x,name);
        }
    }



    static class MyselfCount{
        private String id;
        private int cash;

        private Lock lock = new ReentrantLock();
        private Condition _save = lock.newCondition();
        private Condition _draw = lock.newCondition();

        public MyselfCount(String id, int cash) {
            this.id = id;
            this.cash = cash;
        }


        public void save(int x,String name){
            lock.lock();
            if (x>0){
                cash+=x;
                System.out.println(name + "存款" + x + "，当前余额为" + cash);
            }
            _draw.signalAll();
            lock.unlock();
        }

        public void draw(int x , String name){
            lock.lock();
            if (x>cash){
                try {
                    System.out.println(name + "取款" + x + "，当前余额为" + cash+" 不够取，先去赚钱把");
                    _draw.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                cash-=x;
                System.out.println(name + "取款" + x + "，当前余额为" + cash);
            }
            _save.signalAll();
            lock.unlock();
        }
    }
}
