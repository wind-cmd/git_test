// 修改 package 声明为实际对应的包名
package com.example.objectDemo.b;

//父类
public abstract class A {
    int aNumber = 100;
    void mMethod(){
        System.out.println("A.mMethod()");
    }

    public abstract void mAbstractMethod();
}
