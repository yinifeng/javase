package com.hobart.data.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    
    static int count = 0;
    /**
     * 插入排序 可能要移动很多
     * @param args
     */
    public static void main(String[] args) {
//        int[] arr = new int[]{101,34,119,1,-1,89};
        //sort1(arr);

//        System.out.println("原始数组~~");
//        System.out.println(Arrays.toString(arr));
//        sort2(arr);

        //测试8W个数的排序时间  稳定0.7秒多 好像比选择排序快
        int[] arr=new int[80000];
        for (int i=0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);//[0,80000)
        }

        long start=System.currentTimeMillis();
        sort2(arr);
        long end = System.currentTimeMillis();

        System.out.println("耗时："+ (end-start));
        System.out.println("循环的次数："+count);
    }

    /**
     * 推导得出的结果
     * 
     * @param arr
     */
    public static void sort2(int[] arr){
        for (int i=1;i<arr.length;i++){
            count++;
            //定义待插入数据
            int insertVal = arr[i];
            int insertIndex = i - 1;//即arr[1]的前面这个数的下标

            //给insertVal 找到插入位置
            //说明
            //1、insertIndex >=0 保证在给inserVal找插入位置，不越界
            //2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            //3、就需要将arr[insertIndex]后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex +1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环，说明插入位置找到，insertIndex+1
            //arr[insertIndex + 1] =insertVal;
            // 差不多==>
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] =insertVal;
            }
//            System.out.println("第"+i+"次插入");
//            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 逐步推导
     * @param arr
     */
    public static void sort1(int[] arr){
        arr = new int[]{101,34,119,1};
        System.out.println("原始数组");
        System.out.println(Arrays.toString(arr));
        //定义待插入数据
        int insertVal = arr[1];
        int insertIndex = 1 - 1;//即arr[1]的前面这个数的下标
        
        //给insertVal 找到插入位置
        //说明
        //1、insertIndex >=0 保证在给inserVal找插入位置，不越界
        //2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //3、就需要将arr[insertIndex]后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环，说明插入位置找到，insertIndex+1
        arr[insertIndex + 1] =insertVal;
        System.out.println("第一次插入");
        System.out.println(Arrays.toString(arr));

        insertVal = arr[2];
        insertIndex = 2 - 1;//即arr[1]的前面这个数的下标

        //给insertVal 找到插入位置
        //说明
        //1、insertIndex >=0 保证在给inserVal找插入位置，不越界
        //2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //3、就需要将arr[insertIndex]后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环，说明插入位置找到，insertIndex+1
        arr[insertIndex + 1] =insertVal;
        System.out.println("第二次插入");
        System.out.println(Arrays.toString(arr));

        insertVal = arr[3];
        insertIndex = 3 - 1;//即arr[1]的前面这个数的下标

        //给insertVal 找到插入位置
        //说明
        //1、insertIndex >=0 保证在给inserVal找插入位置，不越界
        //2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //3、就需要将arr[insertIndex]后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex +1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环，说明插入位置找到，insertIndex+1
        arr[insertIndex + 1] =insertVal;
        System.out.println("第三次插入");
        System.out.println(Arrays.toString(arr));
        
    }
}
