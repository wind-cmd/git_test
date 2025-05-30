package com.example.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Statement {

    /**
     * 对数据表的增删改
     *
     * @throws SQLException
     */
    static void DMLTest(String url, String user, String password, String sql) throws SQLException {
        //注册驱动
        //        Class.forName("com.mysql.jdbc.Driver");
        //获取链接
        Connection conn = DriverManager.getConnection(url, user, password);
        //执行sql对象
        Statement stmt = conn.createStatement();
        int count = stmt.executeUpdate(sql);
        if (count > 0) {
            System.out.println("执行成功，影响" + count + "行");
        } else {
            System.out.println("执行失败");
        }
        stmt.close();
        conn.close();
    }

    /**
     * 对数据表的创建删除，修改表结构，视图操作等
     *
     * @throws SQLException
     */
    static void DDLTest(String url, String user, String password, String sql) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ssm_db";
        String user = "root";
        String password = "123456";
        //String sql = "insert into account (name,money)values('王五',1000)";
        //String sql = "update account set money = money-500 where id = 1";
//        String sql = "delete from account where id=1";
//        DMLTest(url, user, password, sql);

//        String sql = "drop table if exists account";
//        String sql = "CREATE TABLE `account` (\n" +
//                "   `id` int NOT NULL AUTO_INCREMENT,\n" +
//                "   `name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,\n" +
//                "   `money` int DEFAULT NULL,\n" +
//                "   PRIMARY KEY (`id`)\n" +
//                " ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";
//        DDLTest(url, user, password, sql);
    }
}
