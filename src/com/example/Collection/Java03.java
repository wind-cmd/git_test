package com.example.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Java03 {
    public static void main(String[] args) {
        // Map(键值对存储结构)
        Map <String, String> map = new HashMap<String, String>();
        // 添加对象
        map.put("1", "张三");
        map.put("2", "李四");
        map.put("3", "王五");
        map.put("4", "赵六");
        //遍历集合
        Set <String> set = map.keySet();//获取键的集合
        for (String key : set) {
            System.out.println(key + " " + map.get(key));//获取值
        }
        // 
        // HashMap(哈希表)
    }

}
