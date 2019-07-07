package com.hobart.data.recursion;

/**
 * 8皇后问题
 * 
 * 在一个8X8宫格上
 * 8个棋子摆在宫格上
 * 棋子与棋子间不能在 横 竖 斜线 上
 * 
 * 
 */
public class HuangHou8 {
    //定义一个max表示共有多少个皇后
    int max =8;
    //定义一个数组array，保存皇后放置位置的结果，比如array=｛0，4，7，5，2，6，1，3｝
    //数组角标 表示第几个皇后，值表示表示 这个皇后放在 第几列
    int[] array= new int[max];
    
    //统计 算法的 总次数
    static int count=0;
    public static void main(String[] args) {
        //测试
        HuangHou8 hh=new HuangHou8();
        hh.check(0);
        System.out.printf("出现皇后摆放的总次数%d",count);
    }
    
    //放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有 for(int i=0; i < max;i++) 因此有回溯
    private void check(int n){
        if (n == max){
            //n=8 其实8个皇后就已经放好
            print();
            return;
        }
        //依次放入皇后，并是否冲突
        for (int i=0;i<max;i++){
            //先把当前这个皇后 n，放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){
                //接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行 array[n] = i;即将第n个皇后，放置在本行的 后移的一个位置
            
        }
    }

    /**
     * 校验冲突
     * 
     * 查看当前我们放置的第n个皇后问题，就去检测该皇后是否和前面已经摆放的皇后冲突
     * 
     * @param n 表示第n个皇后
     * @return
     */
    public boolean judge(int n){
        for (int i=0;i<n;i++){
            //说明
            //1、array[i] == array[n] 表示判断第n个皇后是否和前面的第i个皇后在同一列
            //2、Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后是否在同一斜线
            //n = 1 放置第2列 1 n=1 array[1] = 1
            //Math.abs(1-0) == Math.abs(array[1] - array[0])
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    
    private void print(){
        count++;
        for (int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
