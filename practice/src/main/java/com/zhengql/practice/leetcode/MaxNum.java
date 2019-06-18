package com.zhengql.practice.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 描述:
 * 最大数
 *
 * @author zhengql
 * @date 2019/6/11 16:52
 */
public class MaxNum {
    public static void main(String[] args) {
        int a[] = {10,1,20,30,213,213124,3425345,35463,23432,65876,5654,23,123,435,5476,65,7324,2345,23423,546745};
        System.out.println(largestNumber(a));
        System.out.println(largestNumber1(a));
    }

    public static String largestNumber(int[] nums) {
        long start = System.currentTimeMillis();
        String[] sortarr = new String[nums.length];
        int i = 0;
        for(int n : nums){
            sortarr[i++] = String.valueOf(n);
        }
        Arrays.sort(sortarr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });

        if("0".equals(sortarr[0])){
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for(String s : sortarr){
            sb.append(s);
        }
        System.out.println(System.currentTimeMillis()-start);
        return sb.toString();
    }


    public static String largestNumber1(int[] nums) {
        String str="";
        while (nums.length>0){
            //存放每一轮数组中满足要求的最大的数，放于最终字符串的最前面
            int temp=nums[0];
            //每轮比较temp的数组下标
            int tempIndex=0;
            for(int i=1;i<nums.length;i++){
                //先将nums[i]和temp转换成字符串，然后以两种不同的顺序进行连接，最后比较两种结果的大小
                if ((Integer.toString(nums[i])+Integer.toString(temp)).compareTo(Integer.toString(temp)+Integer.toString(nums[i]))>0){
                    //temp赋值为当前位置的数组元素
                    temp = nums[i];
                    //记录下当前数组下标
                    tempIndex=i;
                }
            }
            //将temp添加到字符串str中
            str+=Integer.toString(temp);
            //将temp位置替换成数组的最后一个元素
            nums[tempIndex]=nums[nums.length-1];
            //将数组最后一个元素剔除，数组长度减一
            nums=Arrays.copyOf(nums, nums.length-1);
        }
        if(str.charAt(0)=='0'){

            return "0";
        }
        return str;

    }
}
