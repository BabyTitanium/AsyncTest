package com.example.springboot.entity;

import java.io.Serializable;

public class AsyncFileResult implements Serializable {

    private String key;

    private boolean hasError;

    private String msg;

    private int totalCycle = 1;
    private int currentCycle = 0;

    private String fileAddr;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCurrentCycle() {
        return currentCycle;
    }

    public void setCurrentCycle(int currentCycle) {
        this.currentCycle = currentCycle;
    }

    public String getFileAddr() {
        return fileAddr;
    }

    public void setFileAddr(String fileAddr) {
        this.fileAddr = fileAddr;
    }

    public int getTotalCycle() {
        return totalCycle;
    }

    public void setTotalCycle(int totalCycle) {
        this.totalCycle = totalCycle;
    }
}
