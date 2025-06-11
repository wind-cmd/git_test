package com.example.mapper;

import com.example.pojo.User;

import java.util.List;

public interface UserMapper {
    //查询所有用户
    List<User> selectAll();

    //添加用户
    void addUser(User user);

    //修改用户
    void updateUser(User user);

    //删除用户
    void deleteUser(int id);
    //批量删除
    void deleteAllUsers(int[]ids);
}
