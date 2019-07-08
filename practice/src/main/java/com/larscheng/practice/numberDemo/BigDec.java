package com.larscheng.practice.numberDemo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 描述:
 * BigDecimal
 *
 * 舍去为数值小于5：直接舍去
 * 舍去位数值大于5：进位后舍去
 * 舍去位数值等于5：5后又非零数值进位后舍去
 *                 5后为0，根据5前数字奇偶性判断，奇数进位偶数舍去
 *
 *
 *
 * 代码中涉及金额，货币，优先使用BigDecimal、long，不推荐使用单精度和双精度类型
 * @author zhengql
 * @date 2019/5/16 10:50
 */
public class BigDec {


    /***
     *
     * add:加
     * subtract：减
     * multiply：乘
     * divide：除
     * divide：商
     */






    public BigDecimal divide(BigDecimal a , BigDecimal b){
        return  a.divide(b);
    }


    public BigDecimal multiply(BigDecimal a , BigDecimal b){
        return  a.multiply(b);
    }


    public BigDecimal add(BigDecimal a , BigDecimal b){
        return  a.add(b);
    }

    public BigDecimal subtract(BigDecimal a ,BigDecimal b){
        return a.subtract(b);
    }

    public static void main(String[] args) {
        System.out.println(0.2 + 0.1);
        System.out.println(0.3 - 0.1);
        System.out.println(0.2 * 0.1);
        System.out.println(0.3 / 0.1);
        System.out.println("-------------------------------------");
//
//        double d = 0.1;
//        long l = Double.doubleToLongBits(d);
//        System.out.println(Long.toBinaryString(l));
//        float f = 0.2F;
//        int i = Float.floatToIntBits(f);
//        System.out.println(Integer.toBinaryString(i));
//


        BigDecimal b1 = new BigDecimal(123);
        System.out.println(b1);
        BigDecimal bigDecimal = new BigDecimal(123.115);
        System.out.println(bigDecimal);
        BigDecimal bigDecimal1 = new BigDecimal("123.115");
        System.out.println(bigDecimal1);

        System.out.println(bigDecimal);
        double f1 = bigDecimal.setScale(2,RoundingMode.HALF_EVEN).doubleValue();
//        double f1 = bigDecimal1.setScale(2,RoundingMode.HALF_UP).doubleValue();
        System.out.println("f1-------------"+f1);
//
//        int i = bigDecimal.compareTo(bigDecimal1);
//        System.out.println(i);
//        BigDecimal a = bigDecimal.max(bigDecimal1);
//        System.out.println(a);
//        BigDecimal b = bigDecimal.min(bigDecimal1);
//        System.out.println(b);
//




    }

}
