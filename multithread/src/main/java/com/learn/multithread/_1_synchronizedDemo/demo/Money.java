package com.learn.multithread._1_synchronizedDemo.demo;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/14 14:03
 */
public class Money {
    private  volatile double money = 100;

    public Money() {
        System.out.println("init:"+getMoney());
    }

    public double getMoney() {
        return money;
    }

    public  Money useMoney(double money) {
        this.money -=money;
        return this;
    }
}
