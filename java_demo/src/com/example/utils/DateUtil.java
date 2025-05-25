package com.example.utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);// Sun May 25 12:18:04 CST 2025
        System.out.println(date.getTime());// 1722050884000是从1970年1月1日00:00:00开始的毫秒数
        // 计算计算30天后
        long time = date.getTime() + 1000 * 60 * 60 * 24 * 30;
        System.out.println(new Date(time));// Sun Jun 25 12:18:04 CST 2025

        // 日期格式化
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));// 2025-05-25 12:20:20

        // 日期解析
        String dateStr2 = "2025-05-25 12:20:20";// 字符串转换为日期
        try {
            System.out.println(dateFormat.parse(dateStr2));// Sun May 25 12:20:20 CST 2025
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
