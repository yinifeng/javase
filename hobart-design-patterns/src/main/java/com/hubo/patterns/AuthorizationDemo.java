package com.hubo.patterns;

/**
 * 权限设计，使用二进制，位运算
 *
 */
public class AuthorizationDemo {
    /**
     * 可读
     */
    private static byte read = 0b1;//0000
    /**
     * 可写
     */
    private static byte write = 0b10;//0010
    /**
     * 修改
     */
    private static byte modify = 0b100;//0100
    /**
     * 删除
     */
    private static byte remove = 0b1000;//1000

    public static void main(String[] args) {
        //位运算赋予权限 可读，可写，可删除
        int aa = read | write | remove;//1011
        System.out.println("aa权限十进制=" + aa);
        System.out.println("aa权限二进制=" + Integer.toBinaryString(aa));
        if ((aa & read) != 0) {
            //位运算判断权限
            System.out.println("存在可读权限");
        }else {
            System.out.println("不存在可读权限");
        }
        //位运算删除可读权限
        aa = aa ^ read;
        System.out.println("aa权限二进制=" + Integer.toBinaryString(aa));
        if ((aa & read) != 0) {
            System.out.println("存在可读权限");
        }else {
            System.out.println("不存在可读权限");
        }

        if ((aa & remove) != 0) {
            System.out.println("存在删除权限");
        }else {
            System.out.println("不存在删除权限");
        }
        System.out.println("======================");
        int r = (int)read;
        int w = (int)write;
        int m = (int)modify;
        int d = (int)remove;
        //0表示无任何权限
        System.out.println(0 | r | w | d);

        int bb = 0 | w;
        if ((bb & w) != 0) {
            System.out.println("bb存在可写权限");
        }else {
            System.out.println("bb不存在可写权限");
        }
    }
}
