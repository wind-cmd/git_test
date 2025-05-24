package com.example.Collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Java02 {
    public static void main(String[] args) {
        // Set(无序不可重复)
        // 创建集合
        Set<String> set = new HashSet<String>();
        // 添加对象
        set.add("Hello");
        set.add("World");
        set.add("Java");
        set.add("Hello");
        set.add("World");
        set.add("Java");
        set.add("Hello");
        System.out.println(set.add("123"));
        System.out.println(set.add("123"));
        // 遍历集合
        for (String str : set) {
            System.out.println(str);

        }

        // TreeSet(二叉树)
        TreeSet<String> treeSet = new TreeSet<String>();
        // 添加对象
        treeSet.add("Hello");
        treeSet.add("World");
        treeSet.add("Java");
        treeSet.add("Hello");
        treeSet.add("World");
        treeSet.add("Java");
        treeSet.add("Hello");
        System.out.println(treeSet.add("123"));
        System.out.println(treeSet.add("123"));
        // 遍历集合
        for (String str : treeSet) {
            System.out.println(str);

        }

    }

}
