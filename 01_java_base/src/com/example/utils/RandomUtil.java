package com.example.utils;

import java.util.Random;

public class RandomUtil {

    public static void main(String[] args) {
        Random random = new Random();
        //生成0-n-1的随机数
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(0, 10)); 
        }
    }
}
