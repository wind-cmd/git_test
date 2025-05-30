package com.example.druid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.example.pojo.User;

import javax.sql.DataSource;

public class UserTest {

    public static void addUser() throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/com/example/druid/druid.properties"));
        DataSource dataSource =  DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        String sql = "insert into user (username,password,age,class_name)values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"Druid");
        preparedStatement.setString(2,"123456");
        preparedStatement.setInt(3,18);
        preparedStatement.setString(4,"1班");
        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println("<UNK>");
        }
        preparedStatement.close();
        connection.close();
    }

    public static void selectUser() throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/com/example/druid/druid.properties"));
        DataSource dataSource =  DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        String sql = "select * from user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setAge(resultSet.getInt("age"));
            user.setPassWord(resultSet.getString("password"));
            user.setClassName(resultSet.getString("class_name"));
            users.add(user);
        }
        System.out.println(users);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    public static void deleteUser() throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/com/example/druid/druid.properties"));
        DataSource dataSource =  DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        String sql = "delete from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,159);
        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println("<UNK>");
        }
        preparedStatement.close();
        connection.close();
    }

    public static void updateUser() throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/com/example/druid/druid.properties"));
        DataSource dataSource =  DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        String sql = "update user set class_name = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"9班");
        preparedStatement.setInt(2,157);
        int i = preparedStatement.executeUpdate();
        if(i>0){
            System.out.println("<UNK>");
        }
        preparedStatement.close();
        connection.close();
    }

    public static void main(String[] args) throws Exception {
//        selectUser();
//        addUser();
//        deleteUser();
        updateUser();
    }
}
