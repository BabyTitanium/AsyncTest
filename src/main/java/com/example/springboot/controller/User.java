package com.example.springboot.controller;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String telNum;

    public void setName(String name) {
        this.name = name;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
}
