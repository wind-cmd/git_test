package com.example.IOTest;

import java.io.File;
import java.io.FileOutputStream;

public class OutputStreamTest {
    public static void main(String[] args) {
        File file = new File("E:\\Workspaces\\VSCode\\java_test\\git_test\\java_demo\\file.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write("Hello World!".getBytes());
            //write()是
            //write(byte[] b)是将字节数组b中的所有字节写入到文件中。
            //write(byte[] b, int off, int len)是将字节数组b中的从off开始的len个字节写入到文件中。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
