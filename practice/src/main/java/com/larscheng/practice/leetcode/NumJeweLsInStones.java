package com.larscheng.practice.leetcode;

/**
 * 描述:
 * 宝石与石头
 * <p>
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * 注意:
 * <p>
 * S 和 J 最多含有50个字母。
 * J 中的字符不重复。
 *
 * @author zhengql
 * @date 2019/6/13 15:08
 */
public class NumJeweLsInStones {

    public static void main(String[] args) {
        String s = "asdsdaasd";
        String j = "sawq";
        System.out.println(numJewelsInStones1(s, j));
        System.out.println(numJewelsInStones2(j, s));
    }

    public static int numJewelsInStones1(String J, String S) {
        int num = 0;
        for (int i = 0; i < J.length(); i++) {
            char j = J.charAt(i);
            for (int n = 0; n < S.length(); n++) {
                if (S.charAt(n) == j) {
                    num++;
                }
            }
        }
        return num;
    }

    public static int numJewelsInStones2(String J, String S) {
        int num = 0;
        for (int i = 0; i < S.length(); i++) {
            if (J.indexOf(S.charAt(i)) >= 0) {
                num++;
            }
        }

        return num;
    }

}
