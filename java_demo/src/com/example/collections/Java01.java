package com.example.collectionss;

import java.util.*;

public class Java01 {
    public static void main(String[] args) {
        // 集合体系

        // collections（单个对象存储结构)
        // List(有序可重复)
        // 泛型规定集合中对象的类型，避免类型转换异常
        List<String> list = new ArrayList<String>();
        // 添加对象
        list.add("Hello");
        list.add("World");
        list.add("Java");
        // 遍历集合
        for (String str : list) {
            System.out.println(str);
        }

        // LinkedList(链表)
        // 创建集合
        LinkedList<String> linkedList = new LinkedList<String>();
        // 添加对象
        list.add("Hello");
        list.add("World");
        list.add("Java");
        // 遍历集合
        for (String str : list) {
            System.out.println(str);
        }

    }
}
