package com.example.springboot.controller;

import com.example.springboot.service.MyService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokeHandler implements InvocationHandler {
    MyService myService;
    public MyInvokeHandler (MyService myService) {
        this.myService = myService;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前");
        Object o = method.invoke(myService, args);
        System.out.println("执行后");
        return o;
    }
}
