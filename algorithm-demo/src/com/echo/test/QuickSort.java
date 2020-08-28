package com.echo.test;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 11:58 2020/8/21
 * @description: 快速排序
 */
public class QuickSort {


    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        for (int i = 0; i <= arr.length - 1 ; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i <= arr.length - 1 ; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int temp, i, j;
        temp = arr[left];
        i = left;
        j = right;

        while(i != j) {
            //顺序很重要,先从右往左找
            while(arr[j] >= temp && i < j) {
                j--;
            }
            //再从左往右找
            while(arr[i] <= temp && i < j) {
                i++;
            }

            //交换两个数在数组中的位置
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[left] = arr[i];
        arr[i] = temp;

        //继续处理左边的
        quickSort(arr, left, i - 1);
        //继续处理右边的
        quickSort(arr,i + 1, right);

    }


}
