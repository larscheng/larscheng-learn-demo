package com.learn.multithread._1_synchronizedDemo.demo;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/14 14:10
 */
public class MyErrorDemo implements Runnable {
    public static void main(String[] args) {
        Money money = new Money();
        Thread a  = new Thread(new MyErrorDemo(money),"a");
        Thread b  = new Thread(new MyErrorDemo(money),"b");
        a.start();
        b.start();

    }

    private Money money;

    public MyErrorDemo(Money money) {
        this.money = money;
    }

    @Override
    public void run() {

        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+",num:"+money.useMoney(1).getMoney());

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
