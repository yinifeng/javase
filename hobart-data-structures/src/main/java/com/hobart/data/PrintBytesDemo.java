package com.hobart.data;

/**
 * 打印数字的字节
 *
 * @author hobart
 */
public class PrintBytesDemo {

    /**
     * 一个 int数 在计算机二进制的表示
     * 第一位 表示符合位，1：负数 0：正数
     * 2的31次方-1 是正数最大值  正数 换算10进制，就是所有位为1的2的次方相加 （次方从0开始数 从右往左）
     * 2的31次方 是最小负数
     * 负数的二进制数 换算10进制 非符合位 取反 + 1 然后非符合位 按正数换算方法
     *
     * @param args
     */
    public static void main(String[] args) {
        //2的6次方 + 2的5次方 + 2的2次方
        printBytes(100);

        printBytes(101);
    }

    /**
     *
     * 打印int类型数字
     *
     * int 类型为32位
     *
     * long 类型为64位
     *
     */
    private static void printBytes(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }
}
