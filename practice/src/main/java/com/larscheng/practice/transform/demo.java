package com.larscheng.practice.transform;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/2/18 10:01
 */
public class demo {
        public static void main(String[] args)
        {
            int result;
            try{
                result = foo();
                System.out.println(result);           //输出100
            } catch (Exception e){
                System.out.println(e.getMessage());    //没有捕获到异常
            }

            try{
                result  = bar();
                System.out.println(result);           //输出100
            } catch (Exception e){
                System.out.println(e.getMessage());    //没有捕获到异常
            }
        }

        //catch中的异常被抑制
        @SuppressWarnings("finally")
        public static int foo() throws Exception
        {
            try {
                int a = 5/0;
                return 1;
            }catch(ArithmeticException amExp) {
                throw new Exception("我将被忽略，因为下面的finally中使用了return");
            }finally {
                return 100;
            }
        }

        //try中的异常被抑制
        @SuppressWarnings("finally")
        public static int bar() throws Exception
        {
            try {
                int a = 5/0;
                return 1;
            }finally {
                return 100;
            }
        }


}


