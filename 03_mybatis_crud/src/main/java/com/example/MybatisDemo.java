package com.example;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.pojo.User;

public class MybatisDemo {
    public static void main(String[] args) throws Exception {
        // 加载 MyBatis 配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取 SqlSession
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 执行数据库操作
            List<User> users = session.selectList("test.selectAll");
            System.out.println(users);
            session.close();
        }

    }
}
