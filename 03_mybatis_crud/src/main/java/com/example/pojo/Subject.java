package com.example.pojo;

public class Subject {
    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Subject [id=" + id + ", name=" + name + "]";
    }
    
}
