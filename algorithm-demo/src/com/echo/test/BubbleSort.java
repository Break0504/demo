package com.echo.test;

import com.echo.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 11:08 2020/8/20
 * @description: 冒泡排序 时间复杂度O(N^2)
 */
public class BubbleSort {

    /**
     * 思想：每次比较两个相邻的元素，如果它们的顺序错误就把它们交换过来
     * 原理：每一趟只能确定将一个数归位
     * @param args
     */
    public static void main(String[] args) {
        //printBubbleSort();
        //printBubbleSort(getStudent());
        //collectionSort(getStudent());
        List<Student> list = getStudent();
        Collections.reverse(list);
        list.forEach(student -> System.out.println(student.toString()));
    }

    private static void printBubbleSort() {
        int[] a = {8, 100, 50, 22, 15, 6, 1, 1000, 999, 0};
        int temp;

        //n个数排序，只用进行n-1趟  (为什么需要从1开始)
        for (int i = 1; i <= a.length - 1 ; i++) {

            //从第1位开始比较直到最后一个尚未归位的数 （为什么需要n-1-i）
            for (int j = 0; j <= a.length -1 - i ; j++) {

                //比较大小交换
                if (a[j] < a[j+1]) {
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }

        for (int i = 0; i <= a.length - 1 ; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static void printBubbleSort(List<Student> students) {
        Student temp;
        for (int i = 1; i <= students.size() - 1 ; i++) {
            for (int j = 0; j <= students.size() - 1 - i ; j++) {
                if (students.get(j).getScore() < students.get(j+1).getScore()) {
                    temp = students.get(j);
                    students.set(j, students.get(j+1));
                    students.set(j+1, temp);
                }
            }
        }
        students.forEach(student -> System.out.println(student.toString()));
    }

    private static void collectionSort(List<Student> students) {
        Collections.sort(students);
        Collections.reverse(students);
        students.forEach(student -> System.out.println(student.toString()));
    }

    private static List<Student> getStudent() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("huhu", 5));
        students.add(new Student("haha",  3));
        students.add(new Student("xixi", 5));
        students.add(new Student("hengheng", 2));
        students.add(new Student("gaoshou", 8));
        return students;
    }



































}
