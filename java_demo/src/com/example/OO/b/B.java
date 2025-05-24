package com.example.OO.b;

/*
 * 抽象类
 * 抽象类不能被实例化，只能被继承
 * 抽象类中可以有抽象方法，也可以有非抽象方法
*/
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
