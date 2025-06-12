package com.example;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mapper.ScoreMapper;
import com.example.pojo.Score;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.mapper.UserMapper;
import com.example.pojo.User;

public class MybatisDemo2 {
    public static void main(String[] args) throws Exception {
        // 加载 MyBatis 配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取 SqlSession
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            // 执行数据库操作
            // List<User> users = session.selectList("test.selectAll");
            //获取UserMapper接口代理对象


            //查询所有用户
//            UserMapper userMapper =  session.getMapper(UserMapper.class);
//            List<User> users = userMapper.selectAll();
//            System.out.println(users);

            //查询指定用户分数
           ScoreMapper scoreMapper = session.getMapper(ScoreMapper.class);
           List<Score> scores = scoreMapper.selectOne(1);
           System.out.println(scores);
           
            //按照科目和用户id查询分数
//            ScoreMapper scoreMapper = session.getMapper(ScoreMapper.class);
//            List<Score> scores = scoreMapper.selectByCondition(1,1);
//            System.out.println(scores);

            //按照对象查询
//            ScoreMapper scoreMapper = session.getMapper(ScoreMapper.class);
//            Score score = new Score();
//            score.setSubjectId(3);
//            score.setUserId(1);
//            List<Score>scores = scoreMapper.selectByCondition(score);
//            System.out.println(scores);

            //按照map查询
//            Map map = new HashMap();
//            map.put("subjectId", 3);
//            map.put("userId", 1);
//            ScoreMapper scoreMapper = session.getMapper(ScoreMapper.class);
//            List<Score>scores = scoreMapper.selectByCondition(map);
//            System.out.println(scores);

            //添加用户
//            UserMapper userMapper = session.getMapper(UserMapper.class);
//            User user = new User();
//            user.setPassWord("123456");
//            user.setUserName("<UNK>");
//            user.setAge(18);
//            user.setClassName("六班");
//            userMapper.addUser(user);
//
//            //默认手动提交事务，需要session.commit()，如果openSession(true)关闭事务了。
//            session.commit();
//            System.out.println(user.getId());

            //修改用户
//            UserMapper userMapper = session.getMapper(UserMapper.class);
//            User user = new User();
//            user.setId(161);
//            user.setPassWord("123456");
//            user.setUserName("<UNK>");
//            user.setAge(18);
//            user.setClassName("6班");
//            userMapper.updateUser(user);
//            session.commit();

//            //删除用户
//            UserMapper userMapper = session.getMapper(UserMapper.class);
//            userMapper.deleteUser(161);

            //批量删除用户
            // UserMapper userMapper = session.getMapper(UserMapper.class);
            // int [] ids = {164,165};
            // userMapper.deleteAllUsers(ids);

            //释放资源
            session.close();
        }
    }
}
