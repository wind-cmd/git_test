package com.example.OO.c;

public class ChangFangXing extends Tuxing{
    int chang;
    int kuan;
    public ChangFangXing(int chang, int kuan) {
        this.chang = chang;
        this.kuan = kuan;
    }
    @Override
    void MianJi() {
        System.out.println("长方形的面积为：" + chang * kuan);
    }

    @Override
    void ZhouChang() {
        System.out.println("长方形的周长为：" + 2 * (chang + kuan)); 
    }
}
