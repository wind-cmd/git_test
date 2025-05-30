package com.example.jdbcTest;

import java.sql.*;

public class JDBC_PreparedStatement {

    public static void main(String[] args) throws SQLException {
        //注册驱动
//        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ssm_db?useServerPrepStmts=true&serverTimezone=UTC";
        String user = "root";
        String password = "123456";
        //获取链接
        Connection conn = DriverManager.getConnection(url, user, password);
//        String sql = "select * from user where username = ? and password = ?";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, "admin");
//        ps.setString(2, "admin");
//        ResultSet resultSet = ps.executeQuery();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("username") + " " + resultSet.getString("password"));
//        }

        String username = "admin";
        String password2 = "' or '1' = '1";
        String sql = "select * from user where username = '"+username+"' and password = '"+password2+"'";
        System.out.println(sql);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            System.out.println("登录成功");
            System.out.println(rs.getString("username")+" "+rs.getString("password"));
        }
        stmt.close();
        conn.close();
    }
}
