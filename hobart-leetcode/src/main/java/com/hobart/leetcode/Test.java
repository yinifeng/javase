package com.hobart.leetcode;

public class Test {
    public static void main(String[] args) {
        int i = 2;
        int j = 1;
        int result = i + (j++);
        //先计算 2 + 1 = 3 再 j++ j=2
        System.out.printf("i=%d,j=%d,result=%d\n",i,j,result);
    }
}
