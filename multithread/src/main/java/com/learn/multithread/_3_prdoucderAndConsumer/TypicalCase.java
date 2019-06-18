package com.learn.multithread._3_prdoucderAndConsumer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 * 生产者消费者经典案例模型
 *
 * @author zhengql
 * @date 2018/12/17 15:13
 */
public class TypicalCase {

    public static void main(String[] args) {
        Depot depot = new Depot(1000);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0 ; i<10;i++){
            if (i%2>0){
                pool.submit(new Producer(new Random().nextInt(500),depot));
            }else {

                pool.submit(new Consumer(new Random().nextInt(200),depot));
            }
        }
        pool.shutdown();
    }

    static class Depot{
        private static final int MAX_SIZE = 3000;
        public int currentNum;

        public Depot() {
        }

        public Depot(int currentNum) {
            this.currentNum = currentNum;
        }


        /***
         * 生产业务
         * @param needNum
         */
        public void produce(int needNum){
            while (needNum+currentNum>MAX_SIZE){
                System.out.println("要生产的产品数量" + needNum + "超过剩余库存量" + (MAX_SIZE - currentNum) + "，暂时不能执行生产任务!");
                try {
                    //当前的生产线程等待
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (Depot.class){
                //满足生产条件，则进行生产，这里简单的更改当前库存量
                currentNum += needNum;
                System.out.println("已经生产了" + needNum + "个产品，现仓储量为" + currentNum);
            }
            //唤醒在此对象监视器上等待的所有线程
            notifyAll();
        }


        /**
         * 消费业务
         * @param needNum
         */
        public void consume(int needNum){
            while (needNum>currentNum){
                try {
                    System.out.println("仓储量低于" + needNum + "个产品，请稍等，现仓储量为" + currentNum);
                    //当前的生产线程等待
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (Depot.class) {
                //满足消费条件，则进行消费，这里简单的更改当前库存量
                currentNum -= needNum;
                System.out.println("已经消费了" + needNum + "个产品，现仓储量为" + currentNum);
            }
            //唤醒在此对象监视器上等待的所有线程
            notifyAll();
        }
    }


    static class Producer implements Runnable{
        private int needNum;
        private Depot depot;

        public Producer(int needNum, Depot depot) {
            this.needNum = needNum;
            this.depot = depot;
        }

        @Override
        public void run() {
            depot.produce(needNum);
        }
    }

    static class Consumer implements Runnable{
        private int needNum;
        private Depot depot;

        public Consumer(int needNum, Depot depot) {
            this.needNum = needNum;
            this.depot = depot;
        }

        @Override
        public void run() {
            depot.consume(needNum);
        }
    }
}
