package com.zhengql.practice.leetcode;

/**
 * 描述:
 * 摆动序列
 *
 *
 *
 * @author zhengql
 * @date 2019/6/13 18:27
 */
public class WiggleMaxLength {


    /***
     * 贪心法+动态规划
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2){
            return n;
        }
        int a = 0;
        int b = 0;
        for (int i = 1; i < n; i++) {

            if (nums[i-1]>nums[i]){
                a=b+1;
            }else if (nums[i-1]<nums[i]){
                b=a+1;
            }

        }

        return Math.max(a,b);

    }
}
