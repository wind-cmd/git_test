package com.example.OOP.duoTai;

public class A {
    static {
        System.out.println("static block");
    }
    {
        System.out.println("instance block");
    }

    public A() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        A a1 = new A();
    }
}
