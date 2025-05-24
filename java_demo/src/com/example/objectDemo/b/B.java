package com.example.OO.b;

// 子类
public class B extends A{

    @Override
    public void mAbstractMethod() {
        // TODO Auto-generated method stub
        System.out.println("B.mAbstractMethod()");
    }
    public static void main(String[] args) {
        B b = new B();
        b.mMethod();
        System.out.println(b.aNumber);
        b.mAbstractMethod();
    }
}
