package com.example.Collection;

import java.util.ArrayList;
import java.util.List;

public class Java04 {
    public static void main(String[] args) {
        int j = 2;
        String str2 = String.valueOf(j);

        String str = "1";
        Integer i = Integer.parseInt(str);// 自动拆箱
        System.out.println(i);

        Double d = Double.parseDouble(str); // 自动拆箱
        System.out.println(d);

        // 集合和数组区别
        // 1.数组的长度是固定的，集合的长度是可变的
        // 2.数组只能存储同一种类型的元素，集合可以存储不同类型的元素List<Object> list = new ArrayList<Object>();
        // 3.数组可以存储基本数据类型和引用数据类型，集合只能存储引用数据类型
        // List<Integer> list = new ArrayList<Integer>();
        // int [] arr = new int[3];
        // int [][] arr = new int[3][3];
        List<Integer> list = new ArrayList<Integer>();// 泛型
        list.add(1);// int Integer 自动存儲
        list.add(2);
        list.add(3);
        for (int num : list) {
            System.out.println(num);
        }
    }

}
