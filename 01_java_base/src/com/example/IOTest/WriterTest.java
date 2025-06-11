package com.example.IOTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class WriterTest {
    public static void main(String[] args) {
        String Path = "E:\\Workspaces\\VSCode\\java_test\\git_test\\java_demo\\file.txt";
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(Path);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Hello World!");
            // bufferedWriter.newLine();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
