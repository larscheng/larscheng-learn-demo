package com.zhengql.practice.transform;

/**
 * @auther: zhengql
 * @date: 2019/5/22 14:05
 * @description:
 */
@FunctionalInterface
public interface functionInter {
    void demo();

    default void demo2() {
        System.out.println(213);
    }

    static void demo3() {

    }


    @Override
    boolean equals(Object object);

}