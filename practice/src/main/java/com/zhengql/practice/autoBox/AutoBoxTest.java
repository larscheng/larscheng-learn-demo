package com.zhengql.practice.autoBox;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/23 09:45
 */
public class AutoBoxTest {
    public static void main(String[] args) {
        Integer a = 30;
        Integer b = 30;

        if (a==b){
            System.out.println("a、b:内存地址相同");
        }else {
            System.out.println("a、b:不同的两个对象");
        }

        Integer c = 300;
        Integer d = 300;

        if (c==d){
            System.out.println("c、d:内存地址相同");
        }else {
            System.out.println("c、d:不同的两个对象");
        }


        Character cc = '\u0000';
        Character c3 = '\u007F';
    }
}
