package com.example.springboot.controller;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyRedisTemplate {
    Map<String, Object> map =new HashMap<>();
    Object object;

    public void setObject(String key, Object object) {
        map.put(key, object);
    }

    public Object getObject(String key) {
        return map.get(key);
    }
}
