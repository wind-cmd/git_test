package com.example.mapper;

import com.example.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoreMapper {
    //根据用户id查询分数
    List<Score> selectOne(int userId);
    //条件查询
//    List<Score> selectByCondition(Score score);
//    List<Score> selectByCondition(@Param("userId") int userId,@Param("subjectId")int SubjectId);
    List<Score> selectByCondition(Map map);
}
