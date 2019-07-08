package com.larscheng.practice.stringDemo;

import java.util.StringJoiner;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/23 14:24
 */
public class StringTest1 {

    public static void main(String[] args) {
        String a = "abcdefghijklmnopqrstuvwxyz";

        a.substring(1,2);


        System.out.println(a.length());

        StringJoiner issueCode = new StringJoiner(",");
        issueCode.add("1").add("2").add("3");
        System.out.println(issueCode.toString());


        StringJoiner stringJoiner = new StringJoiner("/","{","}");
        stringJoiner.add("a").add("c").add("b");
        System.out.println(stringJoiner);
    }
}
