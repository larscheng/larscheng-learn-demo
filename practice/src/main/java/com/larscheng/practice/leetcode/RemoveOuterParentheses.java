package com.larscheng.practice.leetcode;

/**
 * 描述:
 * 删除最外层的括号
 *
 * @author zhengql
 * @date 2019/6/13 15:27
 */
public class RemoveOuterParentheses {
    public static void main(String[] args) {
        System.out.println(removeOuterParentheses1("(()())(())(()(()))"));
        System.out.println(removeOuterParentheses2("(()())(())(()(()))"));
    }

    public static String removeOuterParentheses1(String S){
        int index =0;
        int temp = 0;
        StringBuilder aa=new StringBuilder();
        for (int i = 0;i<S.length();i++){
            if ('('==S.charAt(i)){
                index++;
            }else{
                index--;
            }
            if (index==0){
                aa.append(S, temp+1, i);
                temp=i+1;
            }
        }
        return aa.toString();
    }

    public static String removeOuterParentheses2(String S) {
        if (S == null || S == "") {
            return "";
        }
        int count = 0;
        char[] inputs = S.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inputs.length; i++) {
            char currentChar = inputs[i];
            if (currentChar == '(') {
                if (count > 0) {
                    sb.append(currentChar);
                }
                count++;
            } else {
                count--;
                if (count > 0) {
                    sb.append(currentChar);
                }
            }
        }
        return sb.toString();
    }
}
