package com.example.duoTai;

public class B {
    static{
        System.out.println("B static block");
    }
    {
        System.out.println("B instance block"); 
    }
    public B(){
        System.out.println("B constructor"); 
    }


}
