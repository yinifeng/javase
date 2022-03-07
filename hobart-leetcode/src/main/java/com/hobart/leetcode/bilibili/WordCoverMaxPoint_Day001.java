package com.hobart.leetcode.bilibili;

/**
 *
 * 百度面试题绳子覆盖最多的点数
 *
 * 给定一个有序数组arr，代表坐落在x轴上得点
 * 给定一个正数K,代表绳子的长度
 * 返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 * <p>
 * 例如：
 * [1,3,4,7,13,16,17]
 * 绳子 L =4 如：3-7 就是4 就是盖住3个点 3,5,7
 */
public class WordCoverMaxPoint_Day001 {

    public static void main(String[] args) {
        //前提是数组有序
        int[] arr = new int[]{1, 3, 4, 7, 13, 16, 17};
        int len = 4;
        System.out.println(maxPoint1(arr, len));
        System.out.println(maxPoint2(arr, len));
    }

    /**
     * 解法一：二分 贪心算法
     * [1,3,4,7,13,16,17]
     * L =4 为绳子的长度
     * 绳子的末尾在 1 盖住1个点 1
     * 绳子的末尾在 3 盖住2个点 1,3
     * 绳子的末尾在 4 盖住3个点 1,3,4
     * <p>
     * 每个点都是：logN
     * O(N * logN)
     */
    private static int maxPoint1(int[] arr, int len) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - len);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    private static int nearestIndex(int[] arr, int R, int value) {

        int L = 0;

        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    /**
     * 解法二：滑动窗口
     * [1,3,4,7,13,16,17]
     * 绳子长度 len = 4
     * 定义一个左指针：L
     * 定义一个右指针：R
     * 当 L = R = 0时 1-1 =0 < 4
     * 右移1位 R =1 时 3-1 = 2 <4
     * 右移1位 R =2 时 4 -1 =3 <4
     * 右移1位 R =3 时 7 -1 =6 > 4 那么 L 右移1位 7-3 =4
     * 那么 R 右移1位 13-3 =10 >4 那么 L 右移1位 13-4 =9 以此类推
     * <p>
     * 时间复杂度：O(N)
     */
    private static int maxPoint2(int[] arr, int len) {
        //左指针
        int left = 0;
        //右指针
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= len) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    //for text 暴力解决法
    private static int test(int[] arr, int len) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= len) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

}
