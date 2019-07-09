package com.hobart.data.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 
 * 时间复杂度 O(n^2) n的平方阶
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int arr[] = {3,9,-1,10,-2};
    
        //sort1(arr);
        
        //sort2(arr);

//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
//        sort3(arr);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
        
        //测试冒泡排序速度O(n^2),80000个随机数进行排序 12秒
        int[] arr=new int[80000];
        for (int i=0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);
        }
        
        long start=System.currentTimeMillis();
        sort3(arr);
        long end = System.currentTimeMillis();
        
        System.out.println("耗时："+ (end-start));
    }

    /**
     * 优化性能
     * @param arr
     */
    private static void sort3(int[] arr) {
        int temp = 0;//交换数据用的临时变量
        boolean flag = false;
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    flag = true;//发生了交换，设置为true
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){
                //一次没有发生过交换
                break;//说明是有序的
            }else{
                flag = false;//一次循环设置为false
            }
        }
    }
    
    
    private static void sort2(int[] arr) {
        int temp = 0;//交换数据用的临时变量
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(arr));
        }
    }


    private static void sort1(int[] arr){
        //为了容易理解，把冒泡排序演变过程写一遍
        int temp = 0;//交换数据用的临时变量
        for (int j=0;j<arr.length-1-0;j++){
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        for (int j=0;j<arr.length-1-1;j++){
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        for (int j=0;j<arr.length-1-2;j++){
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        for (int j=0;j<arr.length-1-3;j++){
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组");
        System.out.println(Arrays.toString(arr));
    }
}
