package com.larscheng.practice.stringDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 * 字符串拼接
 * @author zhengql
 * @date 2019/5/30 11:05
 */
public class addTest {

    public static void main(String[] args) {

        String s = "hello";
        for (int i =0 ; i <100;i++){
//            s +="world,";
            s = s+"world,";
        }

        s.equals("a");
        s.hashCode();
//        System.out.println(s);




        int i = 5;
        String i1 = "" + i;
        String i2 = String.valueOf(i);
        String i3 = Integer.toString(i);
        String ss = "a" + "b";


//        System.out.println(i1);

        switchInt(1);
        switchChar('!');
        switchString("aaaaaaaaa");
    }

    private static void switchInt(int a) {
        switch (a){
            case 0:
                System.out.println(a);
                break;
            case 1:
                System.out.println(a);
                break;
            case 2:
                System.out.println(a);
                break;
            default:
                System.out.println("---------------");
                break;
        }
    }
    private static void switchChar(char a) {
        switch (a){
            case 'a':
                System.out.println(a);
                break;
            case 'b':
                System.out.println(a);
                break;
            case 'c':
                System.out.println(a);
                break;
            default:
                System.out.println("---------------");
                break;
        }
    }

    private static void switchString(String a) {
        switch (a){
            case "aaaaaaaaa":
                System.out.println(a);
                break;
            case "bbbbbbb":
                System.out.println(a);
                break;
            case "ddddddddddd":
                System.out.println(a);
                break;
            case "ccccccccccccc":
                System.out.println(a);
                break;
            default:
                System.out.println("---------------");
                break;
        }


        Integer[] integer = new Integer[]{1,2,3};
        List<Integer> list = Arrays.asList(integer);
        List<Integer> int1 = list.stream().filter(integer1 -> integer1>1).collect(Collectors.toList());
        int1.forEach(System.out::println);
    }
}
