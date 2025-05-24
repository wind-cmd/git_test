package com.example.OO.a;

/*
 * static关键字
 * 对象可以访问静态成员，也可以访问非静态成员
 * 类只能访问静态成员，不能访问非静态成员
 *
 */
public class StaTest {
    static int a = 10; // static variable
    int b = 20; // instance variable

    static void test() {
        System.out.println("static method");
    }

    void test2() {
        System.out.println("instance method");
    }

    public static void main(String[] args) {
        // 可以通过类名来访问静态成员
        System.out.println(StaTest.a);
        StaTest.test();

        // 类访问非静态成员调用非静态方法会报错
        // System.out.println(StaTest.b);
        // StaTest.test2();

        // 也可以通过对象来访问静态成员
        StaTest st = new StaTest();
        System.out.println(st.a);
        st.test();

        System.out.println(st.b);
        st.test2();
    }
}
