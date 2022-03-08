package com.hobart.leetcode.bilibili;

/**
 * 有一个数组 只包含G 和 B
 * 相邻的 G和B交互 ：
 * 使G都到 右边 B都到左边
 * 或者
 * 使B都到 右边 G都到左边
 * <p>
 * 两种交互取 出 最小的交互次数
 */
public class MinSwapStep_Day002 {


    /**
     * 一个只有G和B的两种字符的数组
     * 可以让所有的G放左边，所有的B都放右侧
     * 或者可以让所有的B放左边，所有的G都放右侧
     * 但是只能在相邻字符之间进行交换，请问至少需要交换几次
     * <p>
     * <p>
     * 贪心算法
     * <p>
     * 时间复杂度 O(N)
     * <p>
     * GBGGBBBGBG
     * <p>
     * G指针
     *
     * @param str
     * @return
     */
    public static int minStep2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        //G交换的总次数
        int res1 = 0;
        //B交换的总次数
        int res2 = 0;
        //指向G的指针
        int gi = 0;
        //指向B的指针
        int bi = 0;
        for (int i = 0; i < chars.length; i++) {
            //i为移动的指针
            if (chars[i] == 'G') {
                //当前的G，去左边
                res1 += i - (gi++);
            } else {
                //当前的B，去左边
                res2 += i - (bi++);
            }
        }
        System.out.println("G靠左交换的次数=" + res1);
        System.out.println("B靠左交换的次数=" + res2);
        return Math.min(res1, res2);
    }

    public static void main(String[] args) {
        String str = "GBGGBBBGBG";
        System.out.println("交换的最小次数=" + minStep2(str));
    }
}
