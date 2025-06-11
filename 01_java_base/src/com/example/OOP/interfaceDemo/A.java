package com.example.OOP.interfaceDemo;

public class A implements Person{
    @Override
    public void walk(){
        System.out.println("Walking...");
    }
    public static void main(String[] args) {
        A a = new A();
        a.walk();
        System.out.println(Person.a); 
    }

}
