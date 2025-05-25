package com.example.OOP.fengZhuang;

public class Person {
    String name;
    int age;

    public Person() {
        super();
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name; 
    }
    public int getAge() {
        return age; 
    }
    public String getName() {
        return name;
    }
    
}

