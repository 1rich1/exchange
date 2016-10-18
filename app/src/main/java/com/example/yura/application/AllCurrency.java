package com.example.yura.application;

import java.util.ArrayList;


public class AllCurrency {

    private String bankName;
    private ArrayList<MyСurrency> list;

    public AllCurrency(String bankName,ArrayList<MyСurrency> list) {
        this.list = list;
        this.bankName =bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public ArrayList<MyСurrency> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "AllCurrency{" +
                "bankName='" + bankName + '\'' +
                ", list=" + list +
                '}';
    }
}
