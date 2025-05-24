package com.example.OO.e;

import com.example.OO.d.C;

public class E extends C{
    public static void main(String[] args) {
        // 创建 E 类实例
        E e = new E();
        // 通过 E 类实例访问继承的 a 字段
        System.out.println(e.a);
    }
}
