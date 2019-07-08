package com.larscheng.practice.facequestion;

import java.util.Objects;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/2/26 13:28
 */
public class Demo {

    public boolean equals(Demo obj) {
        return true;
    }

    public static void main(String[] args) {
        Object o1 = new Demo();
        Object o2 = new Demo();
        Demo d1 = new Demo();
        Demo d2 = new Demo();

        if (o1.equals(o2)){
            System.out.println("111111111");
        }
        if (d1.equals(d2)){
            System.out.println("222222222");
        }
    }
}
