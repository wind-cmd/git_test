package com.example.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/java/com/example/druid/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(props);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
        System.out.println(System.getProperty("user.dir"));//E:\Workspaces\VSCode\java_test\git_test\web-demo
    }
}
