package com.example.IOTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Filder;

public class ReaderTest {
    public static void main(String[] args) {
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = new FileReader("E:\\Workspaces\\VSCode\\java_test\\git_test\\java_demo\\file.txt");
            // bufferedReader = new BufferedReader(reader);
            // String s = "";
            // while ((s = bufferedReader.readLine()) != null) {
            //     System.out.println(s);
            // }
            int data;
            while ((data = reader.read())!= -1) {
                System.out.println((char)data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (reader != null) {
                    reader.close();

                }

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

    }

}
