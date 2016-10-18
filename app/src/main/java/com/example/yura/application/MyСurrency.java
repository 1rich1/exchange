package com.example.yura.application;

public class MyСurrency {
    private String name;
    private String bay;
    private String sell;

    public MyСurrency(String name, String bay, String sell) {
        this.name = name;
        this.bay = bay;
        this.sell = sell;
    }
    public String getName() {
        return name;
    }

    public String getBay() {
        return bay;
    }

    public String getSell() {
        return sell;
    }
    @Override
    public String toString() {
        return "MyСurrency{" +
                "name='" + name + '\'' +
                ", bay='" + bay + '\'' +
                ", sell='" + sell + '\'' +
                '}';
    }
}
