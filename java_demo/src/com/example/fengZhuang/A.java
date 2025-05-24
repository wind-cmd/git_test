package com.example.fengZhuang;

public class A {
    public static void main(String[] args) {
        //变量方法封装到类里
        //可见性修饰符
        //把现实生活场景用对象表达出来

        //IBM公司
        Com ibm = new Com("IBM");

        //员工
        Emp emp = new Emp("Jack", 20);

        //设置员工
emp.SetCom(ibm);

    }

}
