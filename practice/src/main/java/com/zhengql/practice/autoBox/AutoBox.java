package com.zhengql.practice.autoBox;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2019/5/22 19:21
 */
public class AutoBox {

    private static int test(int i){
        return i+1;
    }
    public static void main(String[] args) {
        int iiii = test(new Integer(1));
        Integer inte = test(1);
        Integer aa = 10;

        int aaa = aa;
        boolean bbbb = aa==1;

        Byte bb = 20;
        byte bbb = bb;

        Short cc = 30;
        short ccc = cc;

        Long d = 40L;
        long dd = d;

        Float e = 50f;
        float ee = e;

        Double f = 60d;
        double ff = f;

        Character g = 'a';
        char gg = g;

        Boolean h = true;
        boolean hh = h;




    }
}
