package com.example.pojo;

public class Score {
    private Integer id;
    private Integer userId;
    private Integer subjectId;
    private double score;
    public void setId(Integer id) {
        this.id = id;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public Integer getId() {
        return id;
    }
    public Integer getUserId() {
        return userId;
    }
    public Integer getSubjectId() {
        return subjectId;
    }
    public double getScore() {
        return score;
    }
    @Override
    public String toString() {
        return "Score [id=" + id + ", userId=" + userId + ", subjectId=" + subjectId + ", score=" + score + "]";
    }

}
