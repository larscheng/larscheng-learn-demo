package com.zhengql.practice.numberDemo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/21 09:59
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
//        BigDecimal b1 = new BigDecimal(1);
//        BigDecimal b2 = new BigDecimal(1.1);
//        BigDecimal b3 = new BigDecimal(111L);
//        BigDecimal b4 = new BigDecimal("1.1");
//        System.out.println(b1);
//        System.out.println(b2);
//        System.out.println(b3);
//        System.out.println(b4);
//
//
//        BigDecimal a = new BigDecimal("6.664");
//        BigDecimal b = new BigDecimal("3");
//
//        System.out.println("a + b =" + a.add(b));
//        System.out.println("a - b =" + a.subtract(b));
//        System.out.println("a * b =" + a.multiply(b));
////        System.out.println("a / b =" + a.divide(b));
//
//
//        System.out.println("a四舍入五保留两位小数："+a.setScale(2,RoundingMode.HALF_UP));
//        System.out.println("a取余运算："+a.remainder(b));
//        System.out.println("a求相反数："+a.negate());
//        System.out.println("a,b比大小："+a.compareTo(b));
//        System.out.println("a以整数形式输出："+a.intValue());
//        System.out.println("a以double形式输出："+a.doubleValue());
//        System.out.println("a以float形式输出："+a.floatValue());
//        System.out.println("a以long形式输出："+a.longValue());
//        System.out.println("a以String形式输出："+a.toString());

        BigDecimal aa = new BigDecimal("3.300");
        BigDecimal bb = new BigDecimal("3.344");
        BigDecimal cc = new BigDecimal("3.355");
        BigDecimal dd = new BigDecimal("3.356");
        BigDecimal ee = new BigDecimal("3.366");
        System.out.println("测试值"+"\t"+"ROUND_UP"+"\t"+"ROUND_DOWN"+"\t"+"ROUND_CEILING"+"\t"+"ROUND_FLOOR"+"\t"+"ROUND_HALF_UP"+"\t"+"ROUND_HALF_DOWN"+"\t"+"ROUND_HALF_EVEN"+"\t"+"ROUND_UNNECESSARY");

//        demo(aa,2);
//        demo(bb,2);
//        demo(cc,2);
//        demo(dd,2);
//        demo(ee,2);
        List<BigDecimal> list = new ArrayList<>();
        list.add(aa);
        list.add(bb);
        list.add(cc);
        list.add(dd);
        list.forEach(a->demo(a,2));
    }


    public static void demo(BigDecimal bigDecimal,int scale){
        System.out.println();
        System.out.print(bigDecimal.toString()+"\t");
        for (int i =0 ;i<8;i++){
            try{
                System.out.print(bigDecimal.setScale(scale,i)+"\t");
            }catch (Exception e){
                System.out.print("ArithmeticException"+"\t");
            }

        }
    }
}
