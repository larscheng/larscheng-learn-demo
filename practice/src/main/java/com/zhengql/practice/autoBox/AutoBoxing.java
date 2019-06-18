package com.zhengql.practice.autoBox;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/22 10:57
 */
public class AutoBoxing {
    public static void main(String[] args) {


        int i = 10;
        //装箱
        Integer ii = i;
        //拆箱
        int iii = ii.intValue();

        System.out.println(iii);


        Integer a = 100;
        Integer b = 100;
        if (a==b){
            //引用判断

            System.out.println("Integer装箱缓存");
        }
        if (a.equals(b)){
            //值判断
            System.out.println("值判断相等");
        }


        System.out.println("-------------------");
        Integer c = 300;
        Integer d = 300;

        if (c==d){
            //引用判断
            System.out.println("Integer装箱缓存");
        }
        if (c.equals(d)){
            //值判断
            System.out.println("值判断相等");
        }

        System.out.println("-------------------");
        Boolean b1 = true;
        Boolean b2 = true;

        if (b1==b2){
            //引用判断
            System.out.println("装箱缓存");
        }
        if (b1.equals(b2)){
            //值判断
            System.out.println("值判断相等");
        }

        System.out.println("-------------------");
        Character c1 = '\u007a';
        Character c2 = '\u007a';



        if (c1==c2){
            //引用判断
            System.out.println("装箱缓存");
        }
        if (c1.equals(c2)){
            //值判断
            System.out.println("值判断相等");
        }

        System.out.println("-------------------");
        Character c3 = '\u008a';
        Character c4 = '\u008a';


        if (c3==c4){
            //引用判断
            System.out.println("装箱缓存");
        }
        if (c3.equals(c4)){
            //值判断
            System.out.println("值判断相等");
        }
    }
}
