package com.larscheng.practice.ExceptionDemo;

import java.util.Objects;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/2/13 15:10
 */
public class RuntimeEx {
    public void transfer(){
        exDemo("");
    }

    public void transfer1(){
        exDemo("");
    }

    private static String exDemo(String str) {
        if ("".equals(str) ||Objects.isNull(str)){
            throw new NullPointerException("param can not be null");
        }
        return 1111+str;
    }

    private static String exDemo2(String str) {
        try {
            exDemo(str);
        }catch (NullPointerException e){
            System.out.println("11111111111");
        }
        return 111+str;
    }
    public static void main(String[] args) {
        new RuntimeEx().transfer1();
    }
}
