package com.echo.test;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 11:00 2020/8/20
 * @description: 简化版桶排序
 */
public class SimpleBinSort {

    /**
     * 时间复杂度O
     * 时间复杂度O(M+N+M+N)=O(2*(M+N))  最小常数可以忽略 也就是O(M+N)
     * 缺点:
     *      1、浪费空间:排序范围n 就需要n个桶
     *      2、局限性:只能排序数字
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {8, 100, 50, 22, 15, 6, 1, 1000, 999, 0};
        int[] b = new int[1001];
        //M
        for (int i = 0; i < b.length ; i++) {
            b[i] = 0;
        }

        //N
        for (int i = 0; i < 10; i++) {
            b[a[i]] ++;
        }

        // M+N
        for (int i = b.length - 1; i > 0; i--) {
            for (int j = 1; j <= b[i] ; j++) {
                System.out.print(i + " ");
            }
        }

    }


}
