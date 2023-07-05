package com.example.springboot.service;

import com.example.springboot.controller.MyInvokeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
@Scope("prototype")
public class UserService implements MyService {
    public void test(){
        System.out.println("test");
    }

    @Override
    public String get() {
        return "123";
    }

    public static void main(String[] args) {
        MyService userService = (MyService) Proxy.newProxyInstance(UserService.class.getClassLoader(), UserService.class.getInterfaces(), new MyInvokeHandler(new UserService()));
        userService.test();
    }
}
