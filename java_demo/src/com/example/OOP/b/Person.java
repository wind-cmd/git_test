package com.example.OOP.b;

/**
 * 抽象类
 * 抽象类是一种特殊的类，它不能被实例化，只能被继承。
 * 抽象类可以包含抽象方法和非抽象方法。
 * 抽象方法是一种没有实现的方法，只有方法的声明，没有方法的实现。
 * 
 * */

public abstract class Person {
    String name;
    int age;

    public Person() {
        super();
    }

    public Person(String name, int age) {
        super();
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
