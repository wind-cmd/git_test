package com.example.pojo;

public class User {
    private int id;
    private String userName;
    private String passWord;
    private int age;
    private String className;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getAge() {
        return age;
    }

    public String getClassName() {
        return className;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", age=" + age + ", className="
                + className + "]";
    }
    
}
