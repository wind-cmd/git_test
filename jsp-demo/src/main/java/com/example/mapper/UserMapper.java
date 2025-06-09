package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //用户登录
    User login(@Param("userNo") String userNo, @Param("passWord") String passWord);
    //根据学号查询用户
    User selectByUserNo(@Param("userNo") String userNo);
    //注册
    @Insert("insert into user (userno,username,password)values(#{userNo},#{userNo},#{passWord})")
    void addUserNo(@Param("userNo") String userNo, @Param("passWord") String passWord);
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
