package com.example.service;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 用户登录
     *
     * @param userNo
     * @param passWord
     * @return
     */
    public User login(String userNo, String passWord) {
        //获取sqlsession
        SqlSession session = factory.openSession();
        //获取userMapper
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //调用方法
        User user = userMapper.login(userNo, passWord);
        //释放资源
        session.close();
        return user;
    }

    /**
     * 用户注册
     */
    public boolean register(User user) {
        //获取sqlsession
        SqlSession session = factory.openSession();
        //获取userMapper
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //用户是否存在
        User u = userMapper.selectByUserNo(user.getUserNo());
        //判断用户是否存在
        if (u == null) {
            userMapper.addUserNo(user.getUserNo(), user.getPassWord());
            session.commit();
        }
        //释放资源
        session.close();
        return u == null;
    }

    /**
     * 通过userno查询
     */
    public boolean selectByUserNo(String userNo) {
        //获取sqlsession
        SqlSession session = factory.openSession();
        //获取userMapper
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //用户是否存在
        User u = userMapper.selectByUserNo(userNo);
        //判断用户是否存在,存在返回false
        return u == null;
    }
}
