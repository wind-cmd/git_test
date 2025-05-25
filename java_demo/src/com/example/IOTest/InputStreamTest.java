package com.example.IOTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class InputStreamTest {
    public static void main(String[] args) {
        File file = new File("E:\\Workspaces\\VSCode\\java_test\\git_test\\java_demo\\file.txt");
        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            // 修正拼写错误，将 intputStream 改为 inputStream
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
            String s = new String(bytes);
            System.out.println(s);

            // 直接用字节流读取输出中文会乱码
            // int data;
            // while((data = inputStream.read()) != -1) {
            // System.out.print((char) data);
            // }

            // 使用字符流读取输出中文不会乱码
            // inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            // int data;
            // while ((data = inputStreamReader.read()) != -1) {
            //     System.out.println((char) data);
            // }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}