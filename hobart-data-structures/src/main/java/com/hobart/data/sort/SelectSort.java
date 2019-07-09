package com.hobart.data.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        //int[] arr= new int[]{101,34,119,1};
        //sort1(arr);
        
        
//        System.out.println("原始数组~~");
//        System.out.println(Arrays.toString(arr));
//        sort2(arr);
//        System.out.println("排序后数组~~~");
//        System.out.println(Arrays.toString(arr));

        //测试8W个数的排序时间  稳定2秒多  比冒泡排序快
        int[] arr=new int[80000];
        for (int i=0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);//[0,80000)
        }

        long start=System.currentTimeMillis();
        sort2(arr);
        long end = System.currentTimeMillis();

        System.out.println("耗时："+ (end-start));
    }

    /**
     * 从推导得出排序
     * 
     * 时间复杂度 O(n^2)
     * 
     * @param arr
     */
    public static void sort2(int[] arr){
        for (int i=0;i < arr.length-1;i++){
            //第一轮
            int minIndex = i;//先假定一个位置
            int min = arr[i];
            for (int j = i +1;j<arr.length;j++){
                if (min > arr[j]){
                    //交换位置
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    /**
     * 选择排序
     * 从小到大
     * 
     * 推导过程
     */
    public static void sort1(int[] arr){
        //推导过程
        //原始数组 101，34，119，1
        arr = new int[]{101,34,119,1};
        System.out.println("原始数组~~");
        System.out.println(Arrays.toString(arr));
        
        //第一轮
        int minIndex = 0;//先假定一个位置
        int min = arr[0];
        for (int j = 0 +1;j<arr.length;j++){
            if (min > arr[j]){
                //交换位置
                min = arr[j];
                minIndex = j;
            }
        }
        if (minIndex != 0){
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一轮交换位置~~~");
        System.out.println(Arrays.toString(arr));

        //第二轮
        minIndex = 1;//先假定一个位置
        min = arr[1];
        for (int j = 1 + 1;j<arr.length;j++){
            if (min > arr[j]){
                //交换位置
                min = arr[j];
                minIndex = j;
            }
        }
        if (minIndex != 1){
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二轮交换位置~~~");
        System.out.println(Arrays.toString(arr));

        //第三轮
        minIndex = 2;//先假定一个位置
        min = arr[2];
        for (int j = 2 + 1;j<arr.length;j++){
            if (min > arr[j]){
                //交换位置
                min = arr[j];
                minIndex = j;
            }
        }
        if (minIndex != 2){
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三轮交换位置~~~");
        System.out.println(Arrays.toString(arr));
    }
}
