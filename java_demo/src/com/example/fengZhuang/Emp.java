package com.example.fengZhuang;

public class Emp {
    String name;
    int age;
    Com com;

    public Emp() {
        super();
    }

    public Emp(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public Com getCom() {
        return com;
    }

    public void setCom(Com com) {
        this.com = com;
    }

    @Override
    public String toString() {
        return "Emp [name=" + name + ", age=" + age + ", com=" + com + "]";
    }
}
