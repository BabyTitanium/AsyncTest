package com.example.springboot.controller;

import com.example.springboot.service.UserService;
import com.example.springboot.tasks.AsyncHandler;
import com.example.springboot.entity.AsyncFileResult;
import com.example.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger("aaa");

    @GetMapping("doTask")
    @ResponseBody
    public String task() {
        File file = new File("a.csv");
        log.info("*****校验并且上传文件******");

        log.info("*****分析文件并且处理******");

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }

        AsyncHandler asyncHandler = new AsyncHandler() {
            @Override
            public void beforeHandle(AsyncFileResult asyncFileResult) {
                int total = list.size();
                if (list.size() > 200) {
                    asyncFileResult.setTotalCycle(total / 200 + 1);
                }
            }

            @Override
            public void doCycle(int cycle) throws Exception {
                int start = cycle * 200;
                int end = cycle * 200 + 200;
                if (start > list.size()) {
                    return;
                }
                if (end > list.size()) {
                    end = list.size();
                }
                List<String> subList = list.subList(start, end);
                log.info("处理 {} - {}条数据", start + 1, end);
                Thread.sleep(2000);
            }

            @Override
            public void exceptionHandle(Exception e) {
                log.error(e.getMessage());
            }
        };
        String key = asyncHandler.start();
        return key;
    }


}
