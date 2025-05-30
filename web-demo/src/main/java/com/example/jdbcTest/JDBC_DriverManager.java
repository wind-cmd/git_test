package com.example.jdbcTest;

import java.sql.*;

public class JDBC_DriverManager {
    public static void main(String[] args) throws SQLException {
        //注册驱动
//        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ssm_db";
        String user = "root";
        String password = "123456";
        //获取链接
        Connection conn = DriverManager.getConnection(url,user,password);
        String sql = "select * from account";
        //执行sql对象
        Statement stmt = conn.createStatement();
        //结果set
        ResultSet resultSet =  stmt.executeQuery(sql);
        //遍历
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int money = resultSet.getInt("money");

            System.out.println("id:"+id+",name:"+name+",money:"+money);
        }
        resultSet.close();
        stmt.close();
        conn.close();
    }


}
