package com.example.OOP.a;

public class Person {

    String name;
    int age;

    public Person() {
        super();
    }
    // 构造方法名和类名相同，没有返回值，没有 void 关键字
    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age; 
    }

    public void run() {
        System.out.println("Running");
    }
    public void jump() {
        System.out.println("Jumping");
    }

    // toString 方法，返回一个字符串，用于打印对象信息
    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    public static void main(String[] args) {
        // 有参创造
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 30);
        // 无参创造
        Person person3 = new Person();

        System.out.println(person1); // 输出：Person{name='Alice', age=25}
        System.out.println(person2); // 输出：Person{name='Bob', age=30}
        System.out.println(person3); // 输出：Person{name='', age=0}

        person3.name = "Charlie";
        person3.age = 35;
        System.out.println(person3); // 输出：Person{name='Charlie', age=35}
        System.out.println(person1.toString());
    }
}
