package com.example.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Connection {

    public static void main(String[] args) throws SQLException {
        //注册驱动
//        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ssm_db";
        String user = "root";
        String password = "123456";
        //获取链接
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = stmt = conn.createStatement();

        try {
            String sql = "update account set money = money+100 where id = 3";
            String sql1 = "update account set money = money-100 where id = 2";

            //开启事务
            conn.setAutoCommit(false);
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            //int i= 1/0;
            int count1 = stmt.executeUpdate(sql1);
            System.out.println(count1);
            //提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            conn.rollback();
        } finally {
            stmt.close();
            conn.close();
        }
    }
}
