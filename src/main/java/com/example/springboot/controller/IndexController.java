package com.example.springboot.controller;

import com.example.springboot.entity.AsyncFileResult;
import com.example.springboot.service.UserService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @Autowired
    MyRedisTemplate redisTemplate;

    @GetMapping("index")
    @ResponseBody
    public String index (@RequestParam String data, HttpServletResponse response) {
        System.out.println(data);
        User user = new User();
        user.setName("lll");
        user.setTelNum("12345555");
        MDC.put("1","1");
        userService.test();
        return userService.toString();
    }

    @GetMapping("task")
    @ResponseBody
    public AsyncFileResult index (@RequestParam String taskId) {
        return (AsyncFileResult)redisTemplate.getObject(taskId);
    }

}
