package com.example.OOP.duoTai;

public class C extends B{
    static {
        System.out.println("C static block"); 
    }
    {
        System.out.println("C instance block");
    }
    Public C(){
        System.out.println("C constructor");
    }
    public static void main(String[] args) {
        C c = new C();
    }

}
