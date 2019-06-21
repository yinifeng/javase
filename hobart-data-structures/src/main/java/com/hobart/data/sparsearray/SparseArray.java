package com.hobart.data.sparsearray;

/**
 * 稀疏数组
 * 
 * 一个棋盘的数据存档
 * 0：表示没有棋子
 * 1：白色棋子
 * 2：黑色棋子
 * <p>
 * 如：
 * </p>
 * 0 0 1 0 0 0 0 0 0
 * 0 0 0 2 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0 0
 * <p>
 * 二维数组：
 * </p>
 * 稀疏数组表示：
 * 第一行表示：多少行 多少列 1和2的个数
 * 第二行表示：多少行 多少列 某个值1
 * 如：
 * 5 9 2
 * 1 3 1
 * 2 4 2
 */
public class SparseArray {
    
    public static void main(String[] args) {
        int[][] array = new int[5][9];
        array[0][2] = 1;
        array[1][3] = 2;

        System.out.println("源二维数组：");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%d\t", array[i][j]);
            }
            System.out.println();
        }
        
        //转换为稀疏数组
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("非0数字的个数:"+sum);
        int[][] sparseArray=new int[sum+1][3];

        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = sum;
        
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }

        System.out.println("稀疏数组：");
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[i].length; j++) {
                System.out.printf("%d\t", sparseArray[i][j]);
            }
            System.out.println();
        }
        
        //稀疏数组转换为二维数组
        System.out.println("转换的稀疏数组");
        int[][] array2=new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            array2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array2[i].length; j++) {
                System.out.printf("%d\t", array2[i][j]);
            }
            System.out.println();
        }
    }
}
