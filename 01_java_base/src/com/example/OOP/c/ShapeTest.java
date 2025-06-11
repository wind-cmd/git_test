package com.example.OOP.c;

public class ShapeTest {
    public static void main(String[] args) {
        // Circle c = new Circle(5);
        // ChangFangXing cfx = new ChangFangXing(5, 10);
        // c.MianJi();
        // c.ZhouChang();
        // cfx.MianJi();
        // cfx.ZhouChang();


        
        //类型转换
        //子类转父类直接转
        //父类转子类需要强制类型转换
        Shape s = new Circle(5);
        s.MianJi();
        s.ZhouChang();
        Circle c = (Circle) s;
        c.MianJi();
    }

}
