package com.learn.multithread._1_synchronizedDemo.demo;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/14 14:10
 */
public class MyDemo implements Runnable {
    public static void main(String[] args) {
        Money money = new Money();
        Thread a  = new Thread(new MyDemo(money),"a");
        Thread b  = new Thread(new MyDemo(money),"b");
        a.start();
        b.start();

    }

    private Money money;

    public MyDemo(Money money) {
        this.money = money;
    }

    @Override
    public void run() {

        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+",money:"+use(1));

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /***
     * 同步方案，同步代码块
     * 或者对money中的usermoney方法进行同步
     * @param d
     * @return
     */
    public double use(double d){
        synchronized (money){
            return money.useMoney(d).getMoney();
        }
    }


}
