package com.example.OOP.fengZhuang;

public class Com {
    String name;

    public Com() {
        super();
    }

    public Com(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Com [name=" + name + "]";
    }
}
