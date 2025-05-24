package com.example.OO.c;

public class Circle extends Tuxing{
    int r;
    public Circle(int r) {
        this.r = r;
    }
    @Override
    void MianJi() {
        System.out.println("圆的面积为：" + Math.PI * r * r);
    }

    @Override
    void ZhouChang() {
        System.out.println("圆的周长为：" + 2 * Math.PI * r); 
    }

}
