package com.example.OO.b;

public abstract class Person {
    String name;
    int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void run(); // 抽象方法，只有定义，没有实现

    public abstract String est(String foodString); // 抽象方法可以有参数和返回类型

    public void jump() {
        System.out.println("Jumping");
    }

    public static void main(String[] args) {
        Person person = new Person();// 抽象类不能被实例化，只能被继承
    }
}
